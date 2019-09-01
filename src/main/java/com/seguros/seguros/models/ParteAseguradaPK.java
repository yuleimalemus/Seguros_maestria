package com.seguros.seguros.models;

import javax.persistence.*;
import java.io.Serializable;

public class ParteAseguradaPK implements Serializable {
    private String numeroPoliza;
    private String placa;
    private String codParte;

    public ParteAseguradaPK(){}

    public ParteAseguradaPK(String numeroPoliza, String placa, String codParte) {
        this.numeroPoliza = numeroPoliza;
        this.placa = placa;
        this.codParte = codParte;
    }

    @Basic
    @Column(name = "nro_poliza")
    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    @Basic
    @Column(name = "placa")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Basic
    @Column(name = "cod_parte")
    public String getCodParte() {
        return codParte;
    }

    public void setCodParte(String codParte) {
        this.codParte = codParte;
    }
}
