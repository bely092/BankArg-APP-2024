package com.lalita.bankargapp.Clases;

public class Contacto {
    private String cbu;
    private String nombreContacto;

    public Contacto(String cbu, String nombreContacto) {
        this.cbu = cbu;
        this.nombreContacto = nombreContacto;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }
}
