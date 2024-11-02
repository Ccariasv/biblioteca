package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Prestamo;
import com.umg.biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;


    @GetMapping("/all")
    private List<Prestamo> ListarPrestamos(){
        return prestamoRepository.findAll();
    }

    @PostMapping("/save")
    public Prestamo SalvarPrestamo(@RequestBody Prestamo prestamo){
        return prestamoRepository.save(prestamo);
    }

    @GetMapping("{id}")
    public ResponseEntity<Prestamo> BuscarPrestamoporID(@PathVariable long id){
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado ." + id));
        return ResponseEntity.ok(prestamo);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Prestamo> EditarPrestamo(@PathVariable long id, @RequestBody Prestamo prestamoRequest) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado: " + id));

        // Solo actualiza los campos que están presentes en la solicitud
        if (prestamoRequest.getLibro() != null) {
            prestamo.setLibro(prestamoRequest.getLibro());
        }
        if (prestamoRequest.getCliente() != null) {
            prestamo.setCliente(prestamoRequest.getCliente());
        }
        if (prestamoRequest.getFechaPrestamo() != null) {
            prestamo.setFechaPrestamo(prestamoRequest.getFechaPrestamo());
        }
        if (prestamoRequest.getFechaLimiteDevolucion() != null) {
            prestamo.setFechaLimiteDevolucion(prestamoRequest.getFechaLimiteDevolucion());
        }
        if (prestamoRequest.getFechaDevolucion() != null) {
            prestamo.setFechaDevolucion(prestamoRequest.getFechaDevolucion());
        }
        if (prestamoRequest.getObservaciones() != null) {
            prestamo.setObservaciones(prestamoRequest.getObservaciones());
        }

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        return ResponseEntity.ok(prestamoGuardado);
    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> BorrarPrestamo(@PathVariable long id){
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado: "+id));
        prestamoRepository.delete(prestamo);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
