package com.ecommerce.mapper;

import com.ecommerce.dto.CategoryRequestDTO;
import com.ecommerce.dto.CategoryResponseDTO;
import com.ecommerce.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDTO toDto(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}
