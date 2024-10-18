package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Editorial;
import com.umg.biblioteca.model.EstadoCl;
import com.umg.biblioteca.repository.EstadoClRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EstadoCliente")
public class EstadoClienteController {

    @Autowired
    private EstadoClRepository estadoClRepository;

    @GetMapping("/all")
    private List<EstadoCl> estadoCls(){
        return estadoClRepository.findAll();
    }

    @PostMapping("/save")
    public EstadoCl guardarEstadoCl(@RequestBody EstadoCl estadoCl){
        return estadoClRepository.save(estadoCl);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstadoCl> obtenerEstadoclId(@PathVariable long id){
        EstadoCl estadoCl = estadoClRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Estado Cliente no encontrado" + id));
        return ResponseEntity.ok(estadoCl);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EstadoCl> editEstadoCl(@PathVariable long id, @RequestBody EstadoCl estadoClRequest){
        EstadoCl estadoCl = estadoClRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Estado Cliente no encontrado" + id));
        estadoCl.setEstado(estadoClRequest.getEstado());
        EstadoCl estadoClEditado = estadoClRepository.save(estadoCl);
        return ResponseEntity.ok(estadoClEditado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEstadoCl(@PathVariable Long id){
        EstadoCl estadoCl = estadoClRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado del cliente no encontrado :" + id));
        estadoClRepository.delete(estadoCl);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}



