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
import com.example.proyectofirebase.model.pojo.Telefono;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

public class InsertFragment extends Fragment {

    private EditText etNumTelef;
    private EditText etMarca;
    private EditText etModelo;
    private EditText etImagen;
    private EditText etVAndroid;
    private Button btInsertar;

    private AndroidViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AndroidViewModel.class);

        etNumTelef = view.findViewById(R.id.etInsertNumTelef);
        etMarca = view.findViewById(R.id.etInsertMarca);
        etModelo = view.findViewById(R.id.etInsertModelo);
        etImagen = view.findViewById(R.id.etInsertImagen);
        etVAndroid = view.findViewById(R.id.etInsertVAndroid);
        btInsertar = view.findViewById(R.id.btInsertFrgm);

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNumTelef.getText()) ||
                        TextUtils.isEmpty(etMarca.getText()) ||
                        TextUtils.isEmpty(etModelo.getText()) ||
                        TextUtils.isEmpty(etImagen.getText()) ||
                        TextUtils.isEmpty(etVAndroid.getText())) {
                    Toast.makeText(v.getContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                } else {

                    String numTelef = etNumTelef.getText().toString();
                    String marca = etMarca.getText().toString();
                    String modelo = etModelo.getText().toString();
                    String foto = etImagen.getText().toString();
                    String vAndroid = etVAndroid.getText().toString();

                    Telefono telefono = new Telefono(numTelef, marca, modelo, vAndroid, foto);

                    viewModel.insertTelefono(telefono);

                    NavHostFragment.findNavController(InsertFragment.this)
                            .navigate(R.id.action_insertFragment_to_listaFragment);
                }
            }
        });
    }
}