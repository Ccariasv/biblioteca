package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.GeneroPersona;
import com.umg.biblioteca.repository.GeneroPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/GeneroPersona")
public class GeneroPersonaController {

    @Autowired
    private GeneroPersonaRepository generoPersonaRepository;

    @GetMapping("/all")
    private List<GeneroPersona> ListarGeneros(){
        return generoPersonaRepository.findAll();
    }

    @PostMapping("/save")
    public GeneroPersona guardarGeneroPersona(@RequestBody GeneroPersona generoPersona){
        return generoPersonaRepository.save(generoPersona);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneroPersona> ListarGeneroPorId(@PathVariable Long id){
        GeneroPersona generoPersona = generoPersonaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero de Persona no encontrado : " + id));
        return ResponseEntity.ok(generoPersona);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GeneroPersona> editarGenero(@PathVariable long id, @RequestBody GeneroPersona generoPersonaRequest){
        GeneroPersona generoPersona = generoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero id :" + id));
        generoPersona.setNombre(generoPersonaRequest.getNombre());
        GeneroPersona GeneroPersonaActualizado = generoPersonaRepository.save(generoPersona);
        return ResponseEntity.ok(GeneroPersonaActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarGeneroPersona(@PathVariable Long id){
        GeneroPersona generoPersona = generoPersonaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero Persona no encontrado :" + id));
        generoPersonaRepository.delete(generoPersona);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}


/*






 */