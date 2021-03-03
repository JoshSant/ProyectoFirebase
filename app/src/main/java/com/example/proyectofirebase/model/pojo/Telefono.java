package com.example.proyectofirebase.model.pojo;

public class Telefono {
    
    private String numTelef, marca, modelo, vAndroid, imagen;

    public Telefono() {
    }

    public Telefono(String numTelef, String marca, String modelo, String vAndroid, String imagen) {
        this.numTelef = numTelef;
        this.marca = marca;
        this.modelo = modelo;
        this.vAndroid = vAndroid;
        this.imagen = imagen;
    }

    public String getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(String numTelef) {
        this.numTelef = numTelef;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getvAndroid() {
        return vAndroid;
    }

    public void setvAndroid(String vAndroid) {
        this.vAndroid = vAndroid;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return"numTelef='" + numTelef + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", vAndroid='" + vAndroid;
    }
}
