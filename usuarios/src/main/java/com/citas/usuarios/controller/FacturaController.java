package com.citas.usuarios.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.FacturaRequest;
import com.citas.usuarios.dto.PrecioTServicio;
import com.citas.usuarios.entity.Citas;
import com.citas.usuarios.entity.Empresa;
import com.citas.usuarios.entity.Factura;
import com.citas.usuarios.entity.Precios;
import com.citas.usuarios.repository.CitaRepository;
import com.citas.usuarios.repository.EmpresaRepository;
import com.citas.usuarios.repository.FacturaRepository;
import com.citas.usuarios.repository.PrecioRepository;


@ResponseStatus(HttpStatus.CREATED)
@RestController
@CrossOrigin(origins="*")
public class FacturaController {

    @Autowired
    private FacturaRepository fatu;

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private PrecioRepository precioRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    @PostMapping("crear/factura/{id}")
public ResponseEntity<Map<String, Object>> crearFactura(@RequestBody FacturaRequest factuCita) { //voy a devolver un response entity que contiene un map

    Map<String, Object> Respuesta = new HashMap<>();

    Long idFacturaController=factuCita.getIdFactura();
    Long idCita = factuCita.getIdCita();
    Integer idPrecio = factuCita.getIDPreci();
    Long idEmpresa = factuCita.getIDEmpresa();

    Citas citasDB = citaRepo.findById(idCita).orElse(null);
    Precios precioDB = precioRepo.findById(idPrecio).orElse(null);
    Empresa empresaDB = empresaRepo.findById(idEmpresa).orElse(null);
    Factura facturaDB=fatu.findById(idFacturaController).orElse(null);

    if (citasDB == null || precioDB == null || empresaDB == null) {
        Respuesta.put("error", "Datos no encontrados");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }
     if(facturaDB !=null){
        Respuesta.put("mensaje","error la cita ya tiene una factura");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Respuesta);
    }

    Factura facturaNueva = new Factura();
    facturaNueva.setFechaEmicion(factuCita.getFechaEmi());
    facturaNueva.setCitaFactura(citasDB);
    facturaNueva.setPrecio(precioDB);
    facturaNueva.setEmpresa(empresaDB);
    facturaNueva.setTotalFactura(factuCita.getTotalFactu());
   
    try {
        fatu.save(facturaNueva);
        Respuesta.put("mensaje", "Factura creada exitosamente");
        System.out.println("mi factura es: "+facturaNueva);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Respuesta);
    } catch (Exception e) {
        Respuesta.put("error", "Error al crear factura");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(Respuesta);
}


@CrossOrigin(origins = "*")
@GetMapping("totasFactura/cita/{idCita}")
public ResponseEntity<List<Factura>>traerFacturaAll(@PathVariable Long idCita){
    List<Factura>facturaCita=fatu.buscarById(idCita);
    return ResponseEntity.ok(facturaCita); // retorna Toda la info de la cita

} 

@CrossOrigin(origins = "*")
@GetMapping("factura/cita/{idCita}")
public ResponseEntity<List<PrecioTServicio>>traerFactura(@PathVariable Long idCita){
    List<PrecioTServicio>facturaCita=fatu.obtenerFacturas(idCita);
    if(facturaCita !=null && !facturaCita.isEmpty()){
        //return ResponseEntity.ok(facturaCita); 
        return ResponseEntity.status(HttpStatus.FOUND).body(facturaCita);
    }
    else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(facturaCita);
    }
 //return ResponseEntity.noContent().build();

     // retorna la info necesaria para ver en el front
} 

    
}


