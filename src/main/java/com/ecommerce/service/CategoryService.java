package com.ecommerce.service;

import com.ecommerce.dto.CategoryRequestDTO;
import com.ecommerce.dto.CategoryResponseDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.repository.CategoryRepository;
import jakarta.validation.Valid;
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

    //get By id
    public CategoryResponseDTO getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: " + id));
        return CategoryMapper.toDto(category);
    }


    //update Category
    public CategoryResponseDTO updateCategory(Long id, @Valid CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found with id: " + id));
        category.setName(dto.getName());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    //delete category
    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
    

}
