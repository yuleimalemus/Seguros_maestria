package com.seguros.seguros.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "partes")
public class Parte {
    @NotEmpty
    private String codigoParte;

    private String descripcion;

    private Integer valor;


    @Id
    @Column(name = "cod_parte", nullable = false, length = 50)
    public String getCodigoParte() {
        return codigoParte;
    }

    public void setCodigoParte(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parte parte = (Parte) o;
        return Objects.equals(codigoParte, parte.codigoParte) &&
                Objects.equals(descripcion, parte.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoParte, descripcion);
    }

    @Override
    public String toString() {
        return "Parte{" +
                "codigoParte='" + codigoParte + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", valor=" + valor +
                '}';
    }
}
