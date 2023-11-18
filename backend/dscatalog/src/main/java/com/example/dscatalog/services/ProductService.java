package com.example.dscatalog.services;


import com.example.dscatalog.dto.ProductDTO;
import com.example.dscatalog.entities.Product;
import com.example.dscatalog.repositories.ProductRepository;
import com.example.dscatalog.services.exceptions.DatabaseException;
import com.example.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class ProductService {

@Autowired
ProductRepository repository;
    // Page já é um stream então foi removido do codigo junto do collectors.tolist
    @Transactional(readOnly= true)
    public Page<ProductDTO> findAll(PageRequest pageRequest) {
        Page<Product>list= repository.findAll(pageRequest);
        return list.map(product -> new ProductDTO(product));


    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj= repository.findById(id);
        Product entity = obj.orElseThrow(()->new ResourceNotFoundException("Produto não encontrada"));
        return new ProductDTO(entity, entity.getCategories());

    }
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
     //   entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ProductDTO(entity);

    }
    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
        try{
            Product entity = repository.getReferenceById(id);
          //  entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){

            throw new ResourceNotFoundException("id"+ " " + id +" "+ "não encontrado");
        }
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
