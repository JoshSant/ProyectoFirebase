package com.example.proyectofirebase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectofirebase.model.Repositorio;
import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.model.pojo.Telefono;

import java.util.List;

public class AndroidViewModel extends androidx.lifecycle.AndroidViewModel{

    private Repositorio repositorio;

    public AndroidViewModel(@NonNull Application application) {
        super(application);
        repositorio = new Repositorio();
    }

    public MutableLiveData<List<Telefono>> getListaTelefonos() {
        return repositorio.getListaTelefonos();
    }
    public MutableLiveData<List<Comprador>> getListaCompradores() {
        return repositorio.getListaCompradores();
    }

    public void insertTelefono(Telefono telefono) {
        repositorio.insertTelefono(telefono);
    }

    public void deleteTelefono(String numTelef) {
        repositorio.deleteTelefono(numTelef);
    }

    public void updateTelefono(Telefono telefono){
        repositorio.updateTelefono(telefono);
    }

    public void insertComprador(Comprador comprador) {
        repositorio.insertComprador(comprador);
    }

    public void deleteComprador(String numTelef) {
        repositorio.deleteComprador(numTelef);
    }

    public boolean singIn(String email, String clave){
        return repositorio.singIn(email, clave);
    }

    public boolean create(String email, String clave){
        return repositorio.create(email, clave);
    }

}
