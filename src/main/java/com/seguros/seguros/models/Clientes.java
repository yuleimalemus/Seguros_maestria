package com.seguros.seguros.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "clientes")
public class Clientes {
    private Long cc;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String direccion;

    @NotEmpty
    private String telefono;

    private Integer estado_civil;

    private Integer edad;



    @Id
    @Column(name = "cc", nullable = false)
    public Long getCc() {
        return cc;
    }

    public void setCc(Long cc) {
        this.cc = cc;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "Direccion", nullable = false, length = 50)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "Telefono", nullable = false, length = 50)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "Estado_Civil")
    public Integer getEstadoCivil() {
        return estado_civil;
    }

    public void setEstadoCivil(Integer estado) {
        this.estado_civil = estado;
    }

    @Basic
    @Column(name = "Edad")
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return estado_civil == clientes.estado_civil &&
                edad == clientes.edad &&
                cc.equals(clientes.cc) &&
                nombre.equals(clientes.nombre) &&
                direccion.equals(clientes.direccion) &&
                telefono.equals(clientes.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cc, nombre, direccion, telefono, estado_civil, edad);
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "cc=" + cc +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado_civil=" + estado_civil +
                ", edad=" + edad +
                '}';
    }
}
