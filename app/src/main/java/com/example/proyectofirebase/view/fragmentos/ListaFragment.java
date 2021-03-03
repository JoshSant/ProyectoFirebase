package com.example.proyectofirebase.view.fragmentos;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofirebase.R;
import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.model.pojo.Telefono;
import com.example.proyectofirebase.view.MainActivity;
import com.example.proyectofirebase.view.adapter.RecyclerAdapter.RecyclerTelefonosAdapter;
import com.example.proyectofirebase.view.adapter.RecyclerAdapter.RecyclerVentasAdapter;
import com.example.proyectofirebase.viewmodel.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private Button btInsertar;
    private Button btConsutar;
    private Button btConsutarVentas;
    private RecyclerView recycler;
    private RecyclerTelefonosAdapter adapterTelefonos;
    private RecyclerVentasAdapter adapterVentas;
    private List<Telefono> listaTelefonos = new ArrayList<>();
    private List<Comprador> listaCompradores = new ArrayList<>();
    private List<Telefono> listaVentas = new ArrayList<>();

    private AndroidViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) view.getContext();

        viewModel = new ViewModelProvider(this).get(AndroidViewModel.class);

        recycler = view.findViewById(R.id.recyclerView);
        btInsertar = view.findViewById(R.id.btInsertar);
        btConsutar = view.findViewById(R.id.btConsultar);
        btConsutarVentas = view.findViewById(R.id.btVentas);

        if(mainActivity.autentificado == false){
            btInsertar.setEnabled(false);
            btConsutarVentas.setEnabled(false);
        }

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListaFragment.this)
                        .navigate(R.id.action_listaFragment_to_insertFragment);
            }
        });

        btConsutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recycler.setHasFixedSize(true);
                adapterTelefonos = new RecyclerTelefonosAdapter(new RecyclerTelefonosAdapter.TelefonoDiff());
                Log.v("xyz", listaTelefonos.toString());
                viewModel.getListaTelefonos().observe(getViewLifecycleOwner(), new Observer<List<Telefono>>() {
                    @Override
                    public void onChanged(List<Telefono> telefonos) {
                        //adapterTelefonos.submitList(telefonos);
                        listaTelefonos = telefonos;
                    }
                });
                //adapter.submitList(listaCoches);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.v("xyz", "Lista" + listaTelefonos.toString());
                            adapterTelefonos.submitList(listaTelefonos);
                            recycler.setAdapter(adapterTelefonos);
                            recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        }catch (NullPointerException e){

                        }
                    }
                }, 1000);

            }
        });
        btConsutarVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recycler.setHasFixedSize(true);
                listaVentas.clear();
                adapterVentas = new RecyclerVentasAdapter(new RecyclerVentasAdapter.VentasDiff());
                Log.v("xyz", listaTelefonos.toString());
                viewModel.getListaTelefonos().observe(getViewLifecycleOwner(), new Observer<List<Telefono>>() {
                    @Override
                    public void onChanged(List<Telefono> telefonos) {
                        listaTelefonos = telefonos;
                    }
                });
                viewModel.getListaCompradores().observe(getViewLifecycleOwner(), new Observer<List<Comprador>>() {
                    @Override
                    public void onChanged(List<Comprador> compradores) {
                        listaCompradores = compradores;
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for(int i = 0 ; i < listaCompradores.size(); i++){
                                for(int y = 0; y < listaTelefonos.size(); y++){
                                    if(listaTelefonos.get(y).getNumTelef().equals(listaCompradores.get(i).getNumTelef())){
                                        listaVentas.add(listaTelefonos.get(y));
                                    }
                                }
                            }

                            Log.v("xyz", "Lista" + listaTelefonos.toString());
                            adapterVentas.submitList(listaVentas);
                            recycler.setAdapter(adapterVentas);
                            recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        }catch (NullPointerException e){

                        }
                    }
                }, 1000);
            }
        });

    }
}