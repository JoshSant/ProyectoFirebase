package com.example.proyectofirebase.model.pojo;

public class Comprador {

    String dni, numTelef, nombre, apellidos;

    public Comprador() {
    }

    public Comprador(String dni, String numTelef, String nombre, String apellidos) {
        this.dni = dni;
        this.numTelef = numTelef;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(String numTelef) {
        this.numTelef = numTelef;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "dni='" + dni + '\'' +
                ", numTelef='" + numTelef + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos;
    }
}
