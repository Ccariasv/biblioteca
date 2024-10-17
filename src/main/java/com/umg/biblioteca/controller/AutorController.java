package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Autor;
import com.umg.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;


    @GetMapping("/all")
    private List<Autor> ListarAutores(){
        return autorRepository.findAll();
    }

    @PostMapping("/save")
    public Autor guardarAutor(@RequestBody Autor autor){
        return autorRepository.save(autor);
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> ListarAutorPorId(@PathVariable Long id){
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado : " + id));
        return ResponseEntity.ok(autor);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable long id, @RequestBody Autor autorRequest){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor id :" + id));
        autor.setNombres(autorRequest.getNombres());
        autor.setApellidos(autorRequest.getApellidos());
        Autor autorActualizado = autorRepository.save(autor);
        return ResponseEntity.ok(autorActualizado);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarAutor(@PathVariable Long id){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado :" + id));
        autorRepository.delete(autor);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

