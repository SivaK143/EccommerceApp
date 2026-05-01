package com.ecommerce.service;

import com.ecommerce.dto.CategoryRequestDTO;
import com.ecommerce.dto.CategoryResponseDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //save category
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO){
        Category category = CategoryMapper.toEntity(categoryRequestDTO);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDto(saved);
    }

    //find all categories
    public List<CategoryResponseDTO> findAll(){
        return categoryRepository.findAll()
                .stream().map(category -> CategoryMapper.toDto(category))
                .toList();
    }


}
