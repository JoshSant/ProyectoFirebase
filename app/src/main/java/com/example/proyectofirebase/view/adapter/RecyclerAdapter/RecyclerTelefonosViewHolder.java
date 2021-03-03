package com.example.proyectofirebase.view.adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofirebase.R;
import com.example.proyectofirebase.view.MainActivity;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

public class RecyclerTelefonosViewHolder extends RecyclerView.ViewHolder{

    private final ImageView imgCoche;
    private final TextView tvDatos;
    private Button btEditar;
    private Button btBorrar;
    private Button btComprar;
    public final ConstraintLayout layout;
    private final View view;

    private AndroidViewModel viewModel;

    public RecyclerTelefonosViewHolder(@NonNull View itemView){
        super(itemView);
        MainActivity mainActivity = (MainActivity) itemView.getContext();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(AndroidViewModel.class);

        view = itemView;
        this.imgCoche=itemView.findViewById(R.id.imgTelefono);
        this.tvDatos=itemView.findViewById(R.id.tvDatos);
        this.btEditar = itemView.findViewById(R.id.btEditar);
        this.btBorrar = itemView.findViewById(R.id.btBorrar);
        this.btComprar = itemView.findViewById(R.id.btVender);
        this.layout=itemView.findViewById(R.id.ConstraintLayoutItem);

        if(mainActivity.autentificado == false){
            btBorrar.setEnabled(false);
            btEditar.setEnabled(false);
        }
    }

    @SuppressLint("ResourceType")
    public void bind(String text, String imagCoche, String numTelef, String versAndroid,
                     String marca, String modelo){
        Bundle bundle = new Bundle();
        tvDatos.setText(text);
        Glide.with(view.getContext()).load(imagCoche).into(imgCoche);

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteTelefono(numTelef);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("numTelef", numTelef);
                bundle.putString("vAndroid", versAndroid);
                bundle.putString("marca", marca);
                bundle.putString("modelo", modelo);
                bundle.putString("foto", imagCoche);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_listaFragment_to_editarFragment, bundle);
            }
        });
        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("numTelef", numTelef);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_listaFragment_to_insertCompradoresFragment, bundle);
            }
        });

    }

    public static RecyclerTelefonosViewHolder create(ViewGroup parent){
            View view= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_recycler,parent,false);
            return new RecyclerTelefonosViewHolder(view);
    }
}
