package com.lalita.bankargapp.Clases;

public class Usuario {


    private int id;
    private String nombre;
    private String apellido;
    private String password;
    private String tipoDoc;
    private String nroDoc;
    private String localidad;
    private int nroCalle;
    private String calle;
    private String fechaNac;
    private String tipoSexo;

    public Usuario(int id, String nombre, String apellido, String password, String tipoDoc, String nroDoc, String localidad, int nroCalle, String calle, String fechaNac, String tipoSexo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.tipoDoc = tipoDoc;
        this.nroDoc = nroDoc;
        this.localidad = localidad;
        this.nroCalle = nroCalle;
        this.calle = calle;
        this.fechaNac = fechaNac;
        this.tipoSexo = tipoSexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getNroCalle() {
        return nroCalle;
    }

    public void setNroCalle(int nroCalle) {
        this.nroCalle = nroCalle;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTipoSexo() {
        return tipoSexo;
    }

    public void setTipoSexo(String tipoSexo) {
        this.tipoSexo = tipoSexo;
    }


}
