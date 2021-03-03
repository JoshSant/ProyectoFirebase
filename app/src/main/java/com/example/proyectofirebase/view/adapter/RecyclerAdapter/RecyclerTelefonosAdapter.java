package com.example.proyectofirebase.view.adapter.RecyclerAdapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.proyectofirebase.model.pojo.Telefono;

public class RecyclerTelefonosAdapter extends ListAdapter<Telefono, RecyclerTelefonosViewHolder> {

    public RecyclerTelefonosAdapter(@NonNull DiffUtil.ItemCallback<Telefono> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerTelefonosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerTelefonosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTelefonosViewHolder holder, int position) {
        Telefono current = getItem(position);
        Log.v("xyz", current.toString());
        holder.bind(current.toString(), current.getImagen(), current.getNumTelef(),
                current.getvAndroid(), current.getMarca(), current.getModelo());
    }

    public static class TelefonoDiff extends DiffUtil.ItemCallback<Telefono> {

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
