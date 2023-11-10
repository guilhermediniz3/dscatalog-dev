package com.example.dscatalog.services;

import com.example.dscatalog.dto.CategoryDTO;
import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;


    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
      List<Category>list= repository.findAll();
      List<CategoryDTO>  listDto = new ArrayList<>();
      for(Category cat: list){
         listDto.add(new CategoryDTO(cat));

      }
      return listDto;
      }

    }


}
