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
import com.example.proyectofirebase.view.MainActivity;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;


public class RegisterFragment extends Fragment {

    private EditText etCorreo;
    private EditText etContraseña;
    private Button btEnviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidViewModel androidViewModel = new ViewModelProvider(this).get(AndroidViewModel.class);
        MainActivity mainActivity = (MainActivity) view.getContext();

        etCorreo = view.findViewById(R.id.etEmail2);
        etContraseña = view.findViewById(R.id.etContraseña2);
        btEnviar = view.findViewById(R.id.btEnviar2);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCorreo.getText()) ||
                        TextUtils.isEmpty(etContraseña.getText())){
                    Toast.makeText(v.getContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    mainActivity.autentificado = androidViewModel.create(etCorreo.getText().toString(), etContraseña.getText().toString());
                    if(mainActivity.autentificado == true){
                        NavHostFragment.findNavController(RegisterFragment.this)
                                .navigate(R.id.action_registerFragment_to_listaFragment);
                        Toast.makeText(v.getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(), "Usuario registrado con ese usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}