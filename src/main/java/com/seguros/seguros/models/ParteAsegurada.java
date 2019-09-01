package com.seguros.seguros.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "partes_aseguradas")
@IdClass(value = ParteAseguradaPK.class)
public class ParteAsegurada implements Serializable {
    private String numeroPoliza;
    private String placa;
    private String codParte;
    private Integer valorAsegurado;


    @Id
    @Column(name = "nro_poliza", nullable = false)
    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    @Id
    @Column(name = "placa")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }


    @Id
    @Column(name = "cod_parte")
    public String getCodParte() {
        return codParte;
    }
    public void setCodParte(String codParte) {
        this.codParte = codParte;
    }

    @Basic
    @Column(name = "vr_asegurado")
    public Integer getValorAsegurado() {
        return valorAsegurado;
    }
    public void setValorAsegurado(Integer valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParteAsegurada that = (ParteAsegurada) o;
        return numeroPoliza.equals(that.numeroPoliza) &&
                placa.equals(that.placa) &&
                codParte.equals(that.codParte) &&
                valorAsegurado.equals(that.valorAsegurado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPoliza, placa, codParte, valorAsegurado);
    }

    @Override
    public String toString() {
        return "ParteAsegurada{" +
                "numeroPoliza='" + numeroPoliza + '\'' +
                ", placa='" + placa + '\'' +
                ", codParte='" + codParte + '\'' +
                ", valorAsegurado=" + valorAsegurado +
                '}';
    }
}
