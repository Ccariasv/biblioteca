package com.umg.biblioteca.controller;


import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Departamento;
import com.umg.biblioteca.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/all")
    private List<Departamento> ListarDepartamentos(){
        return departamentoRepository.findAll();
    }

    @PostMapping("/save")
    public Departamento guardarDepartamento(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> ListaDepartamentoPorId(@PathVariable long id){
        Departamento departamento = departamentoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Departaamento no encontrado : " + id));
        return ResponseEntity.ok(departamento);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Departamento> editarDepartamento(@PathVariable long id, @RequestBody Departamento departamentoRequest){
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado : " + id));
        departamento.setNombre(departamentoRequest.getNombre());
        Departamento departamentoActualziado = departamentoRepository.save(departamento);
        return ResponseEntity.ok(departamentoActualziado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarDepartamento(@PathVariable Long id) {
        Departamento departamento = departamentoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("departemento no encontrado : " + id));
        departamentoRepository.delete(departamento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}



