package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.DetallePrestamo;
import com.umg.biblioteca.repository.DetallePrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Detalle")
public class DetallePrestamoController {

    @Autowired
    private DetallePrestamoRepository detallePrestamoRepository;

    /*
    @GetMapping("/all")
    private List<DetallePrestamo> ListarDetalles(){
        return detallePrestamoRepository.findAll();
    }
    */

    @PostMapping("/save")
    public DetallePrestamo guardarDetallePrestamo(DetallePrestamo detallePrestamo){
        return detallePrestamoRepository.save(detallePrestamo);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetallePrestamo> ListardetallePrestamoId(@PathVariable long id){
        DetallePrestamo detallePrestamo = detallePrestamoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("DetallePrestamo no encontrado" + id));
        return ResponseEntity.ok().body(detallePrestamo);
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<DetallePrestamo> editarDetallePrestamo(@PathVariable long id, @RequestBody DetallePrestamo detallePrestamoRequest){
        DetallePrestamo detalleprestamo = detallePrestamoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("DetallePrestamo no encontrado" + id));
        detalleprestamo.setDescripcion(detallePrestamoRequest.getDescripcion());
        DetallePrestamo detallePrestamoActualizado = detallePrestamoRepository.save(detalleprestamo);
        return  ResponseEntity.ok(detallePrestamoActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Map<String,Boolean>> eliminarDetallePrestamo(@PathVariable Long id){
        DetallePrestamo detallePrestamo = detallePrestamoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Detalle Prestamo no encontrado : " + id));
        detallePrestamoRepository.delete(detallePrestamo);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

