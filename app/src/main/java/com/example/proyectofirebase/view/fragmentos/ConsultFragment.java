package com.example.proyectofirebase.view.fragmentos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.proyectofirebase.R;
import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

import java.util.List;

public class ConsultFragment extends Fragment {

    private TextView tvNumTelef;
    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvVAndroid;
    private TextView tvComprador;
    private ImageView imgTelefono;
    private Button btVolver;

    private String comprador = "";

    private AndroidViewModel viewModel;
    private List<Comprador> listaCompradores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AndroidViewModel.class);

        viewModel.getListaCompradores().observe(getViewLifecycleOwner(), new Observer<List<Comprador>>() {
            @Override
            public void onChanged(List<Comprador> compradores) {
                listaCompradores = compradores;
            }
        });

        tvNumTelef = view.findViewById(R.id.tvNumTelf);
        tvMarca = view.findViewById(R.id.tvMarca);
        tvModelo = view.findViewById(R.id.tvModelo);
        tvVAndroid = view.findViewById(R.id.tvVersAndroid);
        tvComprador = view.findViewById(R.id.tvComprador);
        imgTelefono = view.findViewById(R.id.imgTelefFrgm);
        btVolver = view.findViewById(R.id.btVolver);


        String numTelef = getArguments().getString("numTelef");
        String marca = getArguments().getString("marca");
        String modelo = getArguments().getString("modelo");
        String vAndroid = getArguments().getString("vAndroid");
        String imagen = getArguments().getString("imagen");

        tvNumTelef.setText(numTelef);
        tvMarca.setText(marca);
        tvModelo.setText(modelo);
        tvVAndroid.setText(vAndroid);

        Glide.with(view.getContext()).load(imagen).into(imgTelefono);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<listaCompradores.size(); i++){
                        if(listaCompradores.get(i).getNumTelef().equals(numTelef)){
                            comprador = listaCompradores.get(i).toString();
                            break;
                        }
                    }
                    tvComprador.setText(comprador);
                }catch (NullPointerException e){

                }
            }
        }, 1000);

        NavController navController = Navigation.findNavController(view);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_consultFragment_to_listaFragment);
            }
        });
    }
}