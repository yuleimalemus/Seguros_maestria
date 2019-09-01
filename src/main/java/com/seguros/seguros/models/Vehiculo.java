package com.seguros.seguros.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @NotEmpty
    private String placa;

    @NotEmpty
    private String modelo;

    private String marca;

    private int anno;

    @NotEmpty
    private String serie_carroceria;

    private int vr_comercial;

    private int clienteCC;

    @Id
    @Column(name = "placa", nullable = false)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Basic
    @Column(name = "modelo", nullable = false, length = 40)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "marca", nullable = false, length = 50)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "anno", nullable = false, length = 50)
    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    @Basic
    @Column(name = "serie_carroceria", nullable = false)
    public String getSerie_carroceria() {
        return serie_carroceria;
    }

    public void setSerie_carroceria(String serie_carroceria) {
        this.serie_carroceria = serie_carroceria;
    }

    @Basic
    @Column(name = "vr_comercial", nullable = false)
    public int getVr_comercial() {
        return vr_comercial;
    }

    public void setVr_comercial(int vr_comercial) {
        this.vr_comercial = vr_comercial;
    }

    @Basic
    @Column(name = "cc_cliente", nullable = false)
    public Integer getClienteCC() {
        return clienteCC;
    }

    public void setClienteCC(Integer cc_cliente) {
        this.clienteCC = cc_cliente;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return placa == vehiculo.placa &&
                modelo == vehiculo.modelo &&
                marca == vehiculo.marca &&
                anno == vehiculo.anno &&
                serie_carroceria == vehiculo.serie_carroceria &&
                vr_comercial == vehiculo.vr_comercial &&
                clienteCC == vehiculo.clienteCC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, modelo, marca, anno, serie_carroceria, vr_comercial, clienteCC);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", anno=" + anno +
                ", serie_carroceria='" + serie_carroceria + '\'' +
                ", vr_comercial=" + vr_comercial +
                ", cc_cliente=" + clienteCC +
                '}';
    }
}
