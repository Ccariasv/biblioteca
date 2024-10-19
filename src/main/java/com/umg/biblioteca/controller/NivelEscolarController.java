package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.NivelEscolar;
import com.umg.biblioteca.repository.NivelEscolarRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/NivelEsconalr")
public class NivelEscolarController {

    @Autowired
    private NivelEscolarRepository nivelEscolarRepository;

    @GetMapping("/all")
    private List<NivelEscolar> ListarNivelescolar(){
        return nivelEscolarRepository.findAll();
    }

    @PostMapping("/save")
    public NivelEscolar guardarNivelEscolar(@RequestBody NivelEscolar nivelEscolar){
        return nivelEscolarRepository.save(nivelEscolar);
    }

    @GetMapping("{id}")
    public ResponseEntity<NivelEscolar> buscarporID(@PathVariable long id){
        NivelEscolar nivelEscolar = nivelEscolarRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Nivel Escolar no encontrado: "+id));
        return ResponseEntity.ok(nivelEscolar);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<NivelEscolar> editarNivelEscolar(@PathVariable long id, @RequestBody NivelEscolar nivelEscolarClRequest) {
        NivelEscolar nivelEscolar = nivelEscolarRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Nivel Escolar no encontrado : " + id));

        nivelEscolar.setNombre(nivelEscolarClRequest.getNombre());

        NivelEscolar estadoNivelescolar = nivelEscolarRepository.save(nivelEscolar);
        return ResponseEntity.ok(estadoNivelescolar);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarNivelEscolar(@PathVariable long id) {
        NivelEscolar nivelEscolar= nivelEscolarRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Nivel Escolar no encontrado : " + id));
        nivelEscolarRepository.delete(nivelEscolar);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
