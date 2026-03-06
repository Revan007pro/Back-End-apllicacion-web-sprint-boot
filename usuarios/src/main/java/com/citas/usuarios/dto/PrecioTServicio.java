package com.citas.usuarios.dto;

public class PrecioTServicio {
    private Long idFactura;
    private Long idCita;
    private String nombreCliente;
    private String nombreEmpleado;
    private float valorSinIva;
    private float valorTotal;

    // contructor para hacer el querry en hibernate nota: es importante el orden de envio 
    public PrecioTServicio(Long idFactura, String nombreCliente, String nombreEmpleado, float valorSinIva, float valorTotal, Long idCita) {
        this.idFactura = idFactura;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.valorSinIva = valorSinIva;
        this.valorTotal = valorTotal;
        this.idCita=idCita;
    }
    public PrecioTServicio(){}

    public Long getIdFactura(){return idFactura;}
    public String getNombreCliente(){return nombreCliente;}
    public String getNombreEmpleado(){return nombreEmpleado;}
    public float getValorSinIva(){return valorSinIva;}
    public float getValorTotal(){return valorTotal;}
    public Long getIdCita(){return idCita;}

    public void setIDDFactura(Long idFactura){this.idFactura=idFactura;}
    public void setNombreClienteF(String nombreCliente){this.nombreCliente=nombreCliente;}
    public void setNombreEmpleado(String nombreEmpleado){this.nombreEmpleado=nombreEmpleado;}
    public void setValorSinIva(float valorSinIva){this.valorSinIva=valorSinIva;}
    public void setValorTotal(float valorTotal){this.valorTotal=valorTotal;}
    public void setIdCita(Long idCita){this.idCita=idCita;}

}