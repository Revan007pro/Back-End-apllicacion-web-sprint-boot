package com.citas.usuarios.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Tipo_Servicio")
public class Tipo_Servicio {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_tipo_servicio")
    private Integer idTipoServicio;

    @OneToOne
    @JoinColumn(name="roll",referencedColumnName="roll")
    private Empleados rollTServicio;

    @Column(name="descripcion")
    private String desCripcion;

    @OneToOne
    @JoinColumn(name="id_precio",referencedColumnName="id_precio")
    private Precios idPrecio;

    public Integer getIdTipoServicio(){return idTipoServicio;}
    public Empleados getRollTServicio(){return rollTServicio;}
    public String getDescripcionTipo(){return desCripcion;}
    public Precios getIdPrecioTipo(){return idPrecio;}

    public void setTipoServicio(Integer idTipoServicio){this.idTipoServicio=idTipoServicio;}
    public void setRollTServicio(Empleados rollTServicio){this.rollTServicio=rollTServicio;}
    public void setDescripcionTipo(String desCripcion){this.desCripcion=desCripcion;}
    public void setIdPrecioTipo(Precios idPrecio){this.idPrecio=idPrecio;}

    
}
