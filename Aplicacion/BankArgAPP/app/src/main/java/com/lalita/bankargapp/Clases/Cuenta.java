package com.lalita.bankargapp.Clases;

public class Cuenta {
    private int id_usuario;
    private int id_tipo_cuenta;
    private float saldo;

    public Cuenta(int id_usuario, int idTipoCuenta, float saldo) {
        this.id_usuario = id_usuario;
        id_tipo_cuenta = idTipoCuenta;
        this.saldo = saldo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
