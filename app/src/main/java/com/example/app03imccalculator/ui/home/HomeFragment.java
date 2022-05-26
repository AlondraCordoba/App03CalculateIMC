package com.example.app03imccalculator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app03imccalculator.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // Declaracion de nombres de "var" para el boton y los textview donde se visualizara la informacion (respuesta)
    Button calculateIMCButton;
    TextView type, imc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Declarar las "var" de los datos que se ingresaran, y obtener los mismos mediante
        // el id que se le otrogue a cada uno de ellos.
        final EditText txt_mt = binding.txtMt;
        final EditText txt_kg = binding.txtKg;

        // Se brinda un valor a la variable del tipo/categoria (TextView), del IMC
        // asi como del boton para asi poder manejarlas.
        type = binding.txttypeImc ; // binding.idQueSeOtrogo
        calculateIMCButton = binding.buttonCalculate; // binding.idQueSeOtrogo
        imc = binding.txtIMC; // binding.idQueSeOtrogo

        // Evento setOnClickListener brindado al boton.
        calculateIMCButton.setOnClickListener(new View.OnClickListener() {
            // Ejecucion de la funcion/metodo para poder calcular el IMC, de pasan los parametros (datos)
            // obtenidos anteriormente con el id (altura y peso)
            @Override
            public void onClick(View view) {
                imcCalculateFunction(txt_mt, txt_kg);
            }
        });

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Funcion/Metodo para calcular el IMC, las variables son las que se declararon anteriormente
    // y que se pasan al mandar llamar la duncion/metodo
    public void imcCalculateFunction(EditText txt_mt, EditText txt_kg){
        // Logica para calcular el imc (operaciones, conversiones, validaciones, condicionales)

        // Primero se valida si hay datos para continuar.
        if(txt_mt.getText().length() > 0 && txt_kg.getText().length() > 0){
            // Calculo de la operacion (formula).
            double mtsCalculate = Math.pow(Double.parseDouble(txt_mt.getText().toString()), 2);

            //Obtencion/Calculo del IMC.
            double imcCalculate = Double.parseDouble(txt_kg.getText().toString()) / mtsCalculate;

            // Visualizacion del IMC (resultado).
            imc.setText(Double.toString(imcCalculate).substring(0, 5));

            // Condicionales para saber en base al IMC obtenido a cual categoria/tipo pertenece
            if(imcCalculate <= 15){
                // Mostrar la categoria/tipo en el TextView asignado (declarado/obtenido por id) anteriormente.
                type.setText("SEVERO BAJO PESO");
            }else if (imcCalculate > 16 && imcCalculate <= 18){
                type.setText("BAJO PESO");
            } else if(imcCalculate > 18.5 && imcCalculate <= 25){
                type.setText("NORMAL (peso saludable)");
            } else if(imcCalculate > 25 && imcCalculate <= 30){
                type.setText("SOBREPESO");
            } else if(imcCalculate > 30 && imcCalculate <= 35){
                type.setText("OBESIDAD CLASE I (moderadablente obeso)");
            }else if(imcCalculate > 35 && imcCalculate <= 40){
                type.setText("OBESIDAD CLASE II (severamente obeso)");
            } else if(imcCalculate > 30 && imcCalculate <= 35){
                type.setText("OBESIDAD CLASE III (obesidad morbida)");
            }
        }
    }

}