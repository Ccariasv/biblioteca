package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.EstadoCl;
import com.umg.biblioteca.model.EstadoLb;
import com.umg.biblioteca.repository.EstadoLbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadoCliente")
public class EstadoLibroController {

    @Autowired
    private EstadoLbRepository estadoLbRepository;

    @GetMapping("/all")
    private List<EstadoLb> estadoLibros(){
        return estadoLbRepository.findAll();
    }

    @PostMapping("/save")
    public EstadoLb guardarEstadoLibros(@RequestBody EstadoLb estadoLibro){
        return estadoLbRepository.save(estadoLibro);
    }

    @GetMapping("{id}")
    public ResponseEntity<EstadoLb> obtenerEstadoLbId(@PathVariable long id){
        EstadoLb estadolb = estadoLbRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Estado Cliente no encontrado" + id));
        return ResponseEntity.ok(estadolb);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EstadoLb> editEstadoCl(@PathVariable long id, @RequestBody EstadoLb estadoLbRequest){
        EstadoLb estadoCl = estadoLbRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Estado Cliente no encontrado" + id));
        estadoCl.setEstado(estadoLbRequest.getEstado());
        EstadoLb estadoLbEditado = estadoLbRepository.save(estadoCl);
        return ResponseEntity.ok(estadoLbEditado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEstadoLb(@PathVariable Long id){
        EstadoLb estadoLb = estadoLbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado del cliente no encontrado :" + id));
        estadoLbRepository.delete(estadoLb);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
