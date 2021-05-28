package com.example.primeraapp;

public class listElement {

    public String color;
    public String ejercicio;
    public String descripcion;

    public listElement(String color, String ejercicio, String descripcion) {
        this.color = color;
        this.ejercicio = ejercicio;
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
