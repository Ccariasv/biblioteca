package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.GrupoEtnico;
import com.umg.biblioteca.repository.GrupoEtnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/GrupoEtnico")
public class GrupoEtnicoController {

    @Autowired
    private GrupoEtnicoRepository grupoEtnicoRepository;

    @GetMapping("/all")
    private List<GrupoEtnico> ListarGrupoEtnico(){
        return grupoEtnicoRepository.findAll();
    }

    @PostMapping("/save")
    public GrupoEtnico guardarGrupoEtnico(@RequestBody GrupoEtnico GrupoEtnico){
        return grupoEtnicoRepository.save(GrupoEtnico);
    }

    @GetMapping("{id}")
    public ResponseEntity<GrupoEtnico> ListarEditorialPorId(@PathVariable Long id){
        GrupoEtnico grupoEtnico = grupoEtnicoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero Literario no encontrado : " + id));
        return ResponseEntity.ok(grupoEtnico);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GrupoEtnico> editarAutor(@PathVariable long id, @RequestBody GrupoEtnico GrupoEtnicoRequest){
        GrupoEtnico GrupoEtnico = grupoEtnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor id :" + id));
        GrupoEtnico.setNombre(GrupoEtnicoRequest.getNombre());
        GrupoEtnico grupoEtnicoActualizado = grupoEtnicoRepository.save(GrupoEtnico);
        return ResponseEntity.ok(grupoEtnicoActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarGrupoEtnico(@PathVariable Long id){
        GrupoEtnico GrupoEtnico = grupoEtnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado :" + id));
        grupoEtnicoRepository.delete(GrupoEtnico);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
    
}
