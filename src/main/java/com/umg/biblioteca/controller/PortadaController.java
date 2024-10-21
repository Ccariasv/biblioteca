package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Portada;
import com.umg.biblioteca.repository.PortadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Portada")
public class PortadaController {

    @Autowired
    private PortadaRepository portadaRepository;

    @GetMapping("/all")
    private List<Portada> ListarPortada() {
        return portadaRepository.findAll();
    }


    @PostMapping("/save")
    public Portada Guardarportada(@RequestBody Portada portada) {
        return portadaRepository.save(portada);
    }


    @GetMapping("{id}")
    public ResponseEntity<Portada> ListarPortadaPorId(@PathVariable long id) {
        Portada portada = portadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("portada no encontrado : " + id));
        return ResponseEntity.ok(portada);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Portada> editarPortada(@PathVariable long id, @RequestBody Portada portadaRequest) {
        Portada portada = portadaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("editorial no encontrado : " + id));
        portada.setUrl(portadaRequest.getUrl());
        Portada portadaActualizado = portadaRepository.save(portada);
        return ResponseEntity.ok(portadaActualizado);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarportada(@PathVariable long id) {
        Portada portada = portadaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("editorial no encontrado : " + id));
        portadaRepository.delete(portada);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
