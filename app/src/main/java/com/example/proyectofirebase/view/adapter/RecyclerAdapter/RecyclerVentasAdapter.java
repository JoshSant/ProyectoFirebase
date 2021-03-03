package com.example.proyectofirebase.view.adapter.RecyclerAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.proyectofirebase.R;
import com.example.proyectofirebase.model.pojo.Telefono;

public class RecyclerVentasAdapter extends ListAdapter<Telefono, RecyclerVentasViewHolder> {

    public RecyclerVentasAdapter(@NonNull DiffUtil.ItemCallback<Telefono> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerVentasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerVentasViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVentasViewHolder holder, int position) {
        Telefono current = getItem(position);
        holder.bind(current.toString(), current.getImagen(), current.getNumTelef());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("numTelef", current.getNumTelef());
                bundle.putString("marca", current.getMarca());
                bundle.putString("modelo", current.getModelo());
                bundle.putString("imagen", current.getImagen());
                bundle.putString("vAndroid", current.getvAndroid());

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_listaFragment_to_consultFragment, bundle);
            }
        });
    }

    public static class VentasDiff extends DiffUtil.ItemCallback<Telefono> {

        @Override
        public boolean areItemsTheSame(@NonNull Telefono oldItem, @NonNull Telefono newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Telefono oldItem, @NonNull Telefono newItem) {
            return oldItem.getNumTelef().equals(newItem.getNumTelef());
        }
    }
}
