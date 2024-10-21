package com.umg.biblioteca.controller;

import com.umg.biblioteca.exception.ResourceNotFoundException;
import com.umg.biblioteca.model.Libro;
import com.umg.biblioteca.repository.LibroRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/all")
    public List<Libro> getAllLibros(){
        return libroRepository.findAll();
    }

    @PostMapping("/save")
    public Libro addLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @GetMapping({"id"})
    public ResponseEntity<Libro> getLibroById(@RequestParam long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("libro no encontrado : " + id));
        return ResponseEntity.ok(libro);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Libro> editarLibrp(@PathVariable long id, @RequestBody Libro libroRequest) {
        Libro libro = libroRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("libro no encontrado : " + id));

        libro.setTitulo(libroRequest.getTitulo());
        libro.setAutor(libroRequest.getAutor());
        libro.setEditorial(libroRequest.getEditorial());
        libro.setFechaPublicacion(libroRequest.getFechaPublicacion());
        libro.setIsbn(libroRequest.getIsbn());
        libro.setGeneroLiterario(libroRequest.getGeneroLiterario());
        libro.setDescripcion(libroRequest.getDescripcion());
        libro.setNumeroPaginas(libroRequest.getNumeroPaginas());
        libro.setIdioma(libroRequest.getIdioma());
        libro.setNumeroEdicion(libroRequest.getNumeroEdicion());
        libro.setEstadoLb(libroRequest.getEstadoLb());
        libro.setPortada(libroRequest.getPortada());
        Libro libroActualizado = libroRepository.save(libro);
        return ResponseEntity.ok(libroActualizado);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable long id) {
        Libro libro = libroRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("librp no encontrado : " + id));
        libroRepository.delete(libro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
