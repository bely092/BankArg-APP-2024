package com.lalita.bankargapp.Clases;

public class Contactos {
    private int id_usuario;
    private String contacto;
    private String nombre;


    public Contactos(int idUsuario, String contacto, String nombre) {
        id_usuario = idUsuario;
        this.contacto = contacto;
        this.nombre = nombre;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
