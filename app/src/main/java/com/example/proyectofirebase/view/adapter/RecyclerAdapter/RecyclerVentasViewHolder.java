package com.example.proyectofirebase.view.adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofirebase.R;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

public class RecyclerVentasViewHolder extends RecyclerView.ViewHolder{

    private final ImageView imgTelefono;
    private final TextView tvDatos;
    private Button btBorrar;
    public final ConstraintLayout layout;
    private final View view;

    private AndroidViewModel viewModel;

    public RecyclerVentasViewHolder(@NonNull View itemView){
        super(itemView);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(AndroidViewModel.class);

        view = itemView;
        this.imgTelefono=itemView.findViewById(R.id.imagenVenta);
        this.tvDatos=itemView.findViewById(R.id.tvVenta);
        this.btBorrar = itemView.findViewById(R.id.btBorrarVenta);
        this.layout=itemView.findViewById(R.id.constraintVenta);
    }

    @SuppressLint("ResourceType")
    public void bind(String text, String imagTelef, String numTelef){
        tvDatos.setText(text);
        Glide.with(view.getContext()).load(imagTelef).into(imgTelefono);

        Log.v("xyz", text);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteComprador(numTelef);
            }
        });

    }

    public static RecyclerVentasViewHolder create(ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ventas,parent,false);
        return new RecyclerVentasViewHolder(view);
    }
}
