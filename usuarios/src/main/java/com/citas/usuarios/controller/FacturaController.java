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
import org.springframework.web.bind.annotation.PutMapping;
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

  @PostMapping("crear/factura/{idCita}")
public ResponseEntity<Map<String, Object>> crearFactura(
    @PathVariable Long idCita, @RequestBody FacturaRequest factuCita
) {
    Map<String, Object> Respuesta = new HashMap<>();


    Long idPrecio = factuCita.getIdPrecio();
    Long idEmpresa = factuCita.getIDEmpresa();
    String fechaEmi = factuCita.getFechaEmi();

    if(fechaEmi==null || fechaEmi.isEmpty()){
         Respuesta.put("error", "la fecha es nulo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }

    if (idCita == null ) {
        Respuesta.put("error", "el id de la empresa es nulo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }
     if (idPrecio == null ) {
        Respuesta.put("error", "el id precio es nulo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }
     if (idEmpresa == null ) {
        Respuesta.put("error", "el id de la empresa es nulo");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }
     if (idEmpresa == null && idPrecio == null && idCita == null ) {
        Respuesta.put("error", "todos los datos son nulos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Respuesta);
    }

    try {
        Citas citasDB = citaRepo.findById(idCita).orElse(null);
        Precios precioDB = precioRepo.findById(idPrecio).orElse(null);
        Empresa empresaDB = empresaRepo.findById(idEmpresa).orElse(null);

        if (citasDB == null || precioDB == null || empresaDB == null) {
            Respuesta.put("error", "Uno de los registros no existe en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Respuesta);
        }

        double valorBase = precioDB.getValorPrecio(); 
        double iva = valorBase * 0.19;
        double totalConIva = valorBase + iva;

        Factura facturaNueva = new Factura();
        facturaNueva.setCitaFactura(citasDB);
        facturaNueva.setPrecio(precioDB);
        facturaNueva.setEmpresa(empresaDB);
        facturaNueva.setTotalFactura((float)totalConIva);
        facturaNueva.setFechaEmicion(fechaEmi);
   
        fatu.save(facturaNueva);
        
        Respuesta.put("mensaje", "Factura creada exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(Respuesta);

    } catch (Exception e) {
        Respuesta.put("error", "Error al procesar la factura: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Respuesta);
    }
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
@CrossOrigin(origins="*")
@PutMapping("/borrar/factura/{idFactu}")
public ResponseEntity<Map<String,Object>>borrarFactura(@PathVariable long idFactu){
    Map<String,Object>deleteFactu=new HashMap<>();

    try{
        if(!fatu.existsById(idFactu)){
            deleteFactu.put("mensaje","error su factura no se encuentra");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deleteFactu);
        }
        if(fatu.existsById(idFactu)){
            fatu.deleteById(idFactu);
            deleteFactu.put("mensaje","su factura ha sido eliminada satisfactoriamente");
            return ResponseEntity.status(HttpStatus.OK).body(deleteFactu);
        }
    }catch(Exception err){
        deleteFactu.put("mensaje","error en el servidor no se borro su factura");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deleteFactu);
    }
    return ResponseEntity.ok(deleteFactu);
}

    
}


