package com.seguros.seguros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "poliza")
public class Poliza {
    private String numeroPoliza;

    @NotEmpty
    private String tipo;

    private Integer cc_cliente;

    @NotEmpty
    private String codigoCorredor;

    private String placa;

    private Float valor_pagar;
    private Date fecha_inicio;
    private Date fecha_fin;

    private List<Parte> partes;


    @Id
    @Column(name = "nro_poliza", nullable = false)
    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numero) {
        this.numeroPoliza = numero;
    }

    @Basic
    @Column(name = "tipo_poliza", nullable = false, length = 50)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "cc_cliente", nullable = false, length = 50)
    public Integer getCcCliente() {
        return cc_cliente;
    }

    public void setCcCliente(Integer cc_cliente) {
        this.cc_cliente = cc_cliente;
    }

    @Basic
    @Column(name = "codigo_corredor", nullable = false, length = 50)
    public String getCodigoCorredor() {
        return codigoCorredor;
    }

    public void setCodigoCorredor(String codigoCorredor) {
        this.codigoCorredor = codigoCorredor;
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
    @Column(name = "vr_pagar")
    public Float getValor_pagar() {
        return valor_pagar;
    }

    public void setValor_pagar(Float valor_pagar) {
        this.valor_pagar = valor_pagar;
    }

    @Basic
    @Column(name = "fecha_inicio")
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    @Basic
    @Column(name = "fecha_fin")
    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Transient
    public List<Parte> getPartes() {
        return partes;
    }
    public void setPartes(List<Parte> partes) {
        this.partes = partes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poliza poliza = (Poliza) o;
        return numeroPoliza.equals(poliza.numeroPoliza) &&
                tipo.equals(poliza.tipo) &&
                cc_cliente.equals(poliza.cc_cliente) &&
                codigoCorredor.equals(poliza.codigoCorredor) &&
                placa.equals(poliza.placa) &&
                valor_pagar.equals(poliza.valor_pagar) &&
                fecha_inicio.equals(poliza.fecha_inicio) &&
                fecha_fin.equals(poliza.fecha_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPoliza, tipo, cc_cliente, codigoCorredor, placa, valor_pagar, fecha_inicio, fecha_fin);
    }

    @Override
    public String toString() {
        return "Poliza{" +
                "numeroPoliza='" + numeroPoliza + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cc_cliente=" + cc_cliente +
                ", codigoCorredor='" + codigoCorredor + '\'' +
                ", placa='" + placa + '\'' +
                ", valor_pagar=" + valor_pagar + '\'' +
                ", fecha_inicio=" + fecha_inicio + '\'' +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}
