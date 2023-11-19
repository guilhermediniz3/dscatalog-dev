package com.example.dscatalog.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dscatalog.dto.ProductDTO;
import com.example.dscatalog.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    public static  Integer contador=0;
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        // parametros: page, size, sort

        Page<ProductDTO> list = service.findAll(pageable);
        contador++;

        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/incrementar")
    public int incrementarContador() {
        contador++;
        return contador;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {

        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {

        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")

    public ResponseEntity<Void> elete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}