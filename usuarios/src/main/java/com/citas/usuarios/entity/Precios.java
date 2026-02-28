package com.citas.usuarios.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Precios")
public class Precios {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_precio")
    private Integer idPrecio;

    @Column(name="valor")
    private float valorPrecio;

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public float getValorPrecio() {
        return valorPrecio;
    }

    public void setValorPrecio(float valorPrecio) {
        this.valorPrecio = valorPrecio;
    }
}