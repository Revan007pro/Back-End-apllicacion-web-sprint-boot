package com.citas.usuarios.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citas.usuarios.dto.PrecioTServicio;
import com.citas.usuarios.entity.Factura;
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    @Query("SELECT f FROM Factura f WHERE f.citaFactu.idCita = :idCita")
    List<Factura> buscarById(@Param("idCita") Long idCita);
@Query("""
        SELECT new com.citas.usuarios.dto.PrecioTServicio(
            f.idFactura,
            f.citaFactu.usuario.nombre,
            f.citaFactu.empleado.usuario.nombre,
            f.precio.valorPrecio,
            f.totalFactura
        )
        FROM Factura f
        """)
    List<PrecioTServicio> obtenerFacturas(@Param("id_cita") Long id_cita);

}