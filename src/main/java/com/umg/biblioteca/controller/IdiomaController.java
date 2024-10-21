package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Idioma;
import com.umg.biblioteca.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/idioma")
public class IdiomaController {

    @Autowired
    private IdiomaRepository idiomaRepository;

    @GetMapping("/all")
    private List<Idioma> ListarIdiomas() {
        return idiomaRepository.findAll();
    }

    @PostMapping("/save")
    public Idioma guardarIdioma(@RequestBody Idioma Idioma) {
        return idiomaRepository.save(Idioma);
    }

    @GetMapping("{id}")
    public ResponseEntity<Idioma> ListarIdiomaPorId(@PathVariable Long id) {
        Idioma Idioma = idiomaRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Genero Literario no encontrado : " + id));
        return ResponseEntity.ok(Idioma);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Idioma> editarAutor(@PathVariable long id, @RequestBody Idioma IdiomaRequest) {
        Idioma Idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor id :" + id));
        Idioma.setNombre(IdiomaRequest.getNombre());
        Idioma IdiomaActualizado = idiomaRepository.save(Idioma);
        return ResponseEntity.ok(IdiomaActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarIdioma(@PathVariable Long id) {
        Idioma Idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado :" + id));
        idiomaRepository.delete(Idioma);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}


