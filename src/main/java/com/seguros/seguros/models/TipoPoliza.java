package com.seguros.seguros.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "tipo_poliza")
public class TipoPoliza {
    private String codTipo;
    private String descripcion_tipo;
    private String clase;

    @Id
    @Column(name = "cod_tipo", nullable = false)
    public String getCodTipo() {
        return codTipo;
    }
    public void setCodTipo(String cod_tipo) {
        this.codTipo = cod_tipo;
    }

    @Basic
    @Column(name = "descripcion_tipo", nullable = false, length = 50)
    public String getDescripcion_tipo() {
        return descripcion_tipo;
    }
    public void setDescripcion_tipo(String descripcion_tipo) {
        this.descripcion_tipo = descripcion_tipo;
    }

    @Basic
    @Column(name = "clase", nullable = false, length = 50)
    public String getClase() {
        return clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }

}
