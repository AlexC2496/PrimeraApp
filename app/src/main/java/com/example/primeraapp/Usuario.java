package com.example.primeraapp;


    public class Usuario {
        public String nombre, email, telefono, sexo, imagen;

        public Usuario(){

        }
        public Usuario(String nombre, String email, String telefono,String sexo){
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
            this.sexo = sexo;

        }
        public Usuario(String nombre, String email, String telefono,String sexo, String imagen){
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
            this.sexo = sexo;
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
    }

