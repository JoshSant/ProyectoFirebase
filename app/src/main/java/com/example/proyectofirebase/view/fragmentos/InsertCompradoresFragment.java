package com.example.proyectofirebase.view.fragmentos;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.proyectofirebase.R;
import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

public class InsertCompradoresFragment extends Fragment {

    private EditText etDni;
    private EditText etNombre;
    private EditText etApellidos;
    private Button btInsertar;

    private AndroidViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert_compradores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AndroidViewModel.class);

        etDni = view.findViewById(R.id.etInsertDni);
        etNombre = view.findViewById(R.id.etInsertNombre);
        etApellidos = view.findViewById(R.id.etInsertApellidos);
        btInsertar = view.findViewById(R.id.btInsertComprFrgm);

        String numTelef = getArguments().getString("numTelef");

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etDni.getText()) ||
                        TextUtils.isEmpty(etNombre.getText()) ||
                        TextUtils.isEmpty(etApellidos.getText())) {
                    Toast.makeText( v.getContext(),"Rellena todos los campos", Toast.LENGTH_LONG).show();
                }else{

                    String dni = etDni.getText().toString();
                    String nombre = etNombre.getText().toString();
                    String apellidos = etApellidos.getText().toString();

                    Comprador comprador = new Comprador(dni, numTelef, nombre, apellidos);

                    viewModel.insertComprador(comprador);

                    NavHostFragment.findNavController(InsertCompradoresFragment.this)
                            .navigate(R.id.action_insertCompradoresFragment_to_listaFragment);
                }
            }
        });
    }
}