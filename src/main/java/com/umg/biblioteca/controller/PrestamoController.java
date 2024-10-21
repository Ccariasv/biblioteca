package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Prestamo;
import com.umg.biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;


    @GetMapping("/all")
    private List<Prestamo> ListarPrestamos(){
        return prestamoRepository.findAll();
    }

    @PostMapping("/save")
    public Prestamo SalvarPrestamo(@RequestBody Prestamo prestamo){
        return prestamoRepository.save(prestamo);
    }

    @GetMapping("{id}")
    public ResponseEntity<Prestamo> BuscarPrestamoporID(@PathVariable long id){
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado ." + id));
        return ResponseEntity.ok(prestamo);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Prestamo> EditarPrestamo(@PathVariable long id, @RequestBody Prestamo prestamoRequest){
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado ."+ id));
        prestamo.setLibro(prestamoRequest.getLibro());
        prestamo.setCliente(prestamoRequest.getCliente());
        prestamo.setFechaPrestamo(prestamoRequest.getFechaPrestamo());
        prestamo.setFechaLimiteDevolucion(prestamoRequest.getFechaLimiteDevolucion());
        prestamo.setFechaDevolucion(prestamoRequest.getFechaDevolucion());
        prestamo.setObservaciones(prestamoRequest.getObservaciones());
        Prestamo PrestamoGuardado = prestamoRepository.save(prestamo);
        return ResponseEntity.ok(PrestamoGuardado);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> BorrarPrestamo(@PathVariable long id){
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestamo no encontrado: "+id));
        prestamoRepository.delete(prestamo);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
