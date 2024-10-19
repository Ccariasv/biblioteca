package com.umg.biblioteca.controller;


import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Municipio;
import com.umg.biblioteca.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/Municipio")
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;


    @GetMapping("/all")
    private List<Municipio> ListarMunicipio(){
        return municipioRepository.findAll();
    }

    @PostMapping("/save")
    public Municipio guardarMunicipio(@RequestBody Municipio municipio){
        return municipioRepository.save(municipio);
    }


    @GetMapping("{id}")
    public ResponseEntity<Municipio> buscarporID(@PathVariable long id){
        Municipio municipio = municipioRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado: "+id));
        return ResponseEntity.ok(municipio);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Municipio> editarMunicipio(@PathVariable long id, @RequestBody Municipio municipioRequest){

        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipio id :"+ id));
        municipio.setDepartamento(municipioRequest.getDepartamento());
        municipio.setNombre(municipioRequest.getNombre());
        Municipio muncipioActualizado = municipioRepository.save(municipio);
        return  ResponseEntity.ok(muncipioActualizado);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> BorrarMunicipio(@PathVariable long id){
        Municipio municipio = municipioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Municipio no encontrado: "+id));
        municipioRepository.delete(municipio);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}
