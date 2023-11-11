package com.example.dscatalog.services;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import com.example.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
      List<Category>list= repository.findAll();
    return list.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());



    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj= repository.findById(id);
        Category entity = obj.orElseThrow(()->new ResourceNotFoundException("Categoria não encontrada"));
        return new CategoryDTO(entity);

    }
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
     Category entity = new Category();
     entity.setName(dto.getName());
     entity = repository.save(entity);
     return new CategoryDTO(entity);

    }
    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
        try{
    Category entity = repository.getReferenceById(id);
    entity.setName(dto.getName());
    entity = repository.save(entity);
    return new CategoryDTO(entity);
        }
        catch (EntityNotFoundException e){

        throw new ResourceNotFoundException("id"+ " " + id +" "+ "não encontrado");
        }
    }
}
