package com.citas.usuarios.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citas.usuarios.dto.HerrramientaPrestadaDTO;
import com.citas.usuarios.entity.Herramienta;
import com.citas.usuarios.entity.Usuario;
import com.citas.usuarios.repository.HerramientaRepository;
import com.citas.usuarios.repository.UsuarioRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@ResponseStatus

@RestController

@CrossOrigin(origins = "*")
public class HerramientaController {

    @Autowired
    private HerramientaRepository toolRepo;
    @Autowired
    public UsuarioRepository usuarioRepository;

    @GetMapping("/encontrar/herramientas/todas")
    public ResponseEntity<List<Herramienta>> mostrarTool() {
        List<Herramienta> lista = toolRepo.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/prestar/herramienta")
    public ResponseEntity<Map<String, Object>> prestarHerramienta(@RequestBody Map<String, Object> datos) {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            // Long idLibro = (Long) datos.get("id");
            // Long idUsuario = Long.parseLong(datos.get("idUsuario").toString());

            Long idLibro = Long.valueOf(datos.get("id").toString());
            Long idPersona = Long.valueOf(datos.get("idUsuario").toString());

            Usuario usuario = usuarioRepository.findById(idPersona).orElse(null);

            return toolRepo.findById(idLibro).map(tool -> {
                // tool.setUserTool(idUsuario);
                tool.setUserTool(usuario);
                tool.setEstatoTool("Prestado");
                tool.setDisponibleTool(false);

                toolRepo.save(tool);
                respuesta.put("codigo", 1);
                respuesta.put("mensaje", "la herramienta se presto correctamente");
                return ResponseEntity.ok(respuesta);
            }).orElseGet(() -> {
                respuesta.put("codigo", 2);
                respuesta.put("mensaje", "no se encontro la herramienta");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
            });
        } catch (Exception e) {
            respuesta.put("mensaje", "error interno");
            respuesta.put("codigo", 3);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping("listar/herramientas/prestadas")
    public ResponseEntity<Map<String, Object>> listarToolPrestadas() {
        Map<String, Object> respuesta = new HashMap<>();

        try {
            /*
             * List<Herramienta> heramientasPrestadas =
             * toolRepo.findByDisponibleTool(false);
             * List<HerrramientaPrestadaDTO>listaCompleta=toolRepo.stream().map(tool->{
             * Usuario user =
             * usuarioRepository.findById(tool.getId().longValue()).orElse(null);
             * return new HerrramientaPrestadaDTO(tool,user);
             * }).collect(Collectors.toList());
             */
            List<Herramienta> herramientasPrestadas = toolRepo.findByDisponibleTool(false);

            List<HerrramientaPrestadaDTO> listaCompleta = herramientasPrestadas.stream()
                    .map(tool -> new HerrramientaPrestadaDTO(tool))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "herramientas prestadas correctamente");
            respuesta.put("codigo", 1);
            respuesta.put("datos", listaCompleta);

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            respuesta.put("codigo", 2);
            respuesta.put("mensaje", "error al listar las herramientas" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @GetMapping("/herramientas/prestadas/pdf")
    public ResponseEntity<byte[]> pdfPrestar() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            List<Herramienta> listaTool = toolRepo.findByDisponibleTool(false);
            List<HerrramientaPrestadaDTO> listaCompleta = listaTool.stream()
                    .map(tool -> new HerrramientaPrestadaDTO(tool))
                    .collect(Collectors.toList());
            Document documento = new Document(PageSize.A4);
            PdfWriter.getInstance(documento, out); // el out es como el out.putstring
            documento.open();

            Font fuenteTi = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);

            Paragraph titulo = new Paragraph("REPORTE DE HERRAMIENTAS PRESTADAS", fuenteTi);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("")); // espacio en blanco

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(10f);

            Font fuenteHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);

            String[] columns = { "Titulo de la Herramienta", "id Herramienta", "Usuario Prestado", " id Usuario" }; // array
                                                                                                                    // de
                                                                                                                    // tipo
                                                                                                                    // string

            for (String col : columns) {
                PdfPCell cell = new PdfPCell(new Phrase(col, fuenteHeader));
                cell.setBackgroundColor(Color.GRAY);
                cell.setPadding(5);
                tabla.addCell(cell);
            }

            if (listaCompleta.isEmpty()) {
                PdfPCell cellVacia = new PdfPCell(new Phrase("no hay herramientas a prestar actualmente"));
                cellVacia.setColspan(4);
                cellVacia.setPadding(10);
                tabla.addCell(cellVacia);

            } else {
                for (HerrramientaPrestadaDTO dto : listaCompleta) {
                    String tituloTool = (dto.getTituloTool() != null) ? dto.getTituloTool() : "N/A";

                    String idHerramientaStr = (dto.getIdHerramienta() != null) ? String.valueOf(dto.getIdHerramienta())
                            : "N/A";
                    String nombreParaTabla = (dto.getUserTool() != null) ? dto.getUserTool().getNombre()
                            : "Usuario no encontrado";

                    String idUserStr = (dto.getUserTool() != null) ? String.valueOf(dto.getUserTool().getId()) : "N/A";
                    // nota jose busca la forma para insertar el id del empleado

                    tabla.addCell(new Phrase(tituloTool));
                    tabla.addCell(new Phrase(idHerramientaStr));
                    tabla.addCell(new Phrase(nombreParaTabla)); // El nombre que se extrajo
                    tabla.addCell(new Phrase(idUserStr)); // El ID del usuario

                }
            }

            documento.add(tabla);
            documento.close();

            HttpHeaders heders = new HttpHeaders();
            heders.setContentType(MediaType.APPLICATION_PDF);
            heders.setContentDispositionFormData("attacchment", "reporte_herramientas.pdf");
            return new ResponseEntity<>(out.toByteArray(), heders, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
