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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.proyectofirebase.R;
import com.example.proyectofirebase.model.pojo.Telefono;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;


public class EditarFragment extends Fragment {

    private EditText etMarca;
    private EditText etModelo;
    private EditText etImagen;
    private EditText etVAndroid;
    private Button btEditar;

    private AndroidViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AndroidViewModel.class);

        etMarca = view.findViewById(R.id.etEditMarca);
        etModelo = view.findViewById(R.id.etEditModelo);
        etImagen = view.findViewById(R.id.etEditImagen);
        etVAndroid = view.findViewById(R.id.etEditVersAndroid);
        btEditar = view.findViewById(R.id.btEditarFrgm);

        String numTelef = getArguments().getString("numTelef");
        String marca = getArguments().getString("marca");
        String modelo = getArguments().getString("modelo");
        String imagen = getArguments().getString("foto");
        String vAndroid = getArguments().getString("vAndroid");

        etMarca.setText(marca);
        etModelo.setText(modelo);
        etImagen.setText(imagen);
        etVAndroid.setText(vAndroid);

        view.findViewById(R.id.btEditarFrgm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etMarca.getText()) ||
                        TextUtils.isEmpty(etModelo.getText()) ||
                        TextUtils.isEmpty(etImagen.getText()) ||
                        TextUtils.isEmpty(etVAndroid.getText()) ) {
                    Toast.makeText( view.getContext(),"Rellena todos los campos", Toast.LENGTH_LONG).show();
                }else{

                    String marca = etMarca.getText().toString();
                    String modelo = etModelo.getText().toString();
                    String foto = etImagen.getText().toString();
                    String vAndroid = etVAndroid.getText().toString();

                    Telefono telefono = new Telefono(numTelef, marca, modelo, vAndroid, foto);

                    viewModel.updateTelefono(telefono);

                    NavHostFragment.findNavController(EditarFragment.this)
                            .navigate(R.id.action_editarFragment_to_listaFragment);
                }
            }
        });
    }
}