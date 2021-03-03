package com.example.proyectofirebase.model;

import androidx.lifecycle.MutableLiveData;

import com.example.proyectofirebase.FirestoreAplication;
import com.example.proyectofirebase.model.pojo.Comprador;
import com.example.proyectofirebase.model.pojo.Telefono;

import java.util.List;

public class Repositorio {

    private MutableLiveData<List<Telefono>> telefonos;
    private MutableLiveData<List<Comprador>> compradores;
    private FirestoreFunctions firestoreFunctions;

    public Repositorio() {
        this.firestoreFunctions = new FirestoreFunctions();
    }

    public MutableLiveData<List<Telefono>> getListaTelefonos() {
        return firestoreFunctions.readTelefono();
    }

    public MutableLiveData<List<Comprador>> getListaCompradores() {
        return firestoreFunctions.readComprador();
    }

    public void insertTelefono(Telefono telefono) {
        FirestoreAplication.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                firestoreFunctions.addTelefono(telefono);
            }
        });
    }

    public void deleteTelefono(String numTelef){
        new Thread(){
            @Override
            public void run() {
                firestoreFunctions.deleteTelefono(numTelef);
            }
        }.start();
    }

    public void updateTelefono(Telefono telefono){
        new Thread(){
            @Override
            public void run() {
                firestoreFunctions.editTelefono(telefono);
            }
        }.start();
    }

    public void insertComprador(Comprador comprador) {
        FirestoreAplication.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                firestoreFunctions.addComprador(comprador);
            }
        });
    }

    public void deleteComprador(String numTelef){
        new Thread(){
            @Override
            public void run() {
                firestoreFunctions.deleteComprador(numTelef);
            }
        }.start();
    }

    public boolean singIn(String email, String clave){
        firestoreFunctions.singIn(email, clave);
        return firestoreFunctions.autentificado;
    }

    public boolean create(String email, String clave){
        firestoreFunctions.create(email, clave);
        return firestoreFunctions.autentificado;
    }
}
