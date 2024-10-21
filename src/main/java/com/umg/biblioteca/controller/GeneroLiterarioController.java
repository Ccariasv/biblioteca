package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;


import com.umg.biblioteca.model.GeneroLiterario;
import com.umg.biblioteca.repository.GeneroLiterarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Genero_Literario")
public class GeneroLiterarioController {

    @Autowired
    private GeneroLiterarioRepository  generoLiterarioRepository;

    @GetMapping("/all")
    private List<GeneroLiterario> ListarGenerosLiterario(){
        return generoLiterarioRepository.findAll();
    }

    @PostMapping("/save")
    public GeneroLiterario guardarGeneroliterario(@RequestBody GeneroLiterario generoLiterario){
        return generoLiterarioRepository.save(generoLiterario);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneroLiterario> ListarEditorialPorId(@PathVariable Long id){
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero Literario no encontrado : " + id));
        return ResponseEntity.ok(generoLiterario);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GeneroLiterario> editarAutor(@PathVariable long id, @RequestBody GeneroLiterario generoLiterarioRequest){
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor id :" + id));
        generoLiterario.setNombre(generoLiterarioRequest.getNombre());
        GeneroLiterario GeneroLiterarioActualizado = generoLiterarioRepository.save(generoLiterario);
        return ResponseEntity.ok(GeneroLiterarioActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarGeneroLiterario(@PathVariable Long id){
        GeneroLiterario generoLiterario = generoLiterarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado :" + id));
        generoLiterarioRepository.delete(generoLiterario);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
