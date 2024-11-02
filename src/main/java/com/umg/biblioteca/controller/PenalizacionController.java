package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Penalizacion;
import com.umg.biblioteca.repository.PenalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/penalizacion")
public class PenalizacionController {

    @Autowired
    private PenalizacionRepository penalizacionRepository;

    @GetMapping("/all")
    private List<Penalizacion> listarPenalizaciones() {
        return penalizacionRepository.findAll();
    }

    @PostMapping("/save")
    public Penalizacion salvarPenalizacion(@RequestBody Penalizacion penalizacion) {
        return penalizacionRepository.save(penalizacion);
    }

    @GetMapping("{id}")
    public ResponseEntity<Penalizacion> buscarPenalizacionPorID(@PathVariable long id) {
        Penalizacion penalizacion = penalizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalizacion no encontrada: " + id));
        return ResponseEntity.ok(penalizacion);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Penalizacion> editarPenalizacion(@PathVariable long id, @RequestBody Penalizacion penalizacionRequest) {
        Penalizacion penalizacion = penalizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalizacion no encontrada: " + id));

        // Solo actualiza los campos que están presentes en la solicitud
        if (penalizacionRequest.getCliente() != null) {
            penalizacion.setCliente(penalizacionRequest.getCliente());
        }
        if (penalizacionRequest.getPrestamos() != null) {
            penalizacion.setPrestamos(penalizacionRequest.getPrestamos());
        }
        if (penalizacionRequest.getTiempoPenalizacion() != null) {
            penalizacion.setTiempoPenalizacion(penalizacionRequest.getTiempoPenalizacion());
        }
        if (penalizacionRequest.getFechaPenalizacion() != null) {
            penalizacion.setFechaPenalizacion(penalizacionRequest.getFechaPenalizacion());
        }

        Penalizacion penalizacionGuardada = penalizacionRepository.save(penalizacion);
        return ResponseEntity.ok(penalizacionGuardada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> borrarPenalizacion(@PathVariable long id) {
        Penalizacion penalizacion = penalizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalizacion no encontrada: " + id));
        penalizacionRepository.delete(penalizacion);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
