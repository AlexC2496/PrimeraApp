package com.example.primeraapp;

public class Blog {
    private String color;
    private String descripcion;
    private String ejercicio;
    private String url;

    public Blog() {

    }

    public Blog(String color, String descripcion, String ejericio, String url) {
        this.color = color;
        this.descripcion = descripcion;
        this.ejercicio = ejericio;
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejericio) {
        this.ejercicio = ejericio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
