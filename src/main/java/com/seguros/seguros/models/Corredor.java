package com.seguros.seguros.models;

import javax.persistence.*;

@Entity
@Table(name = "corredor")
public class Corredor {
    private String codCorredor;
    private String ccCorredor;
    private String nombreCorredor;
    private String contraseña;

    @Id
    @Column(name = "cod_corredor", nullable = false)
    public String getCodCorredor() { return codCorredor; }
    public void setCodCorredor(String codCorredor) {
        this.codCorredor = codCorredor;
    }

    @Basic
    @Column(name = "cc_corredor", nullable = false)
    public String getCcCorredor() { return ccCorredor; }
    public void setCcCorredor(String ccCorredor) {
        this.ccCorredor = ccCorredor;
    }

    @Basic
    @Column(name = "nombre_corredor", nullable = false, length = 50)
    public String getNombreCorredor() {
        return nombreCorredor;
    }
    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }

    @Basic
    @Column(name = "contrasena", nullable = false, length = 50)
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "codCorredor='" + codCorredor + '\'' +
                ", ccCorredor='" + ccCorredor + '\'' +
                ", nombreCorredor='" + nombreCorredor + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
