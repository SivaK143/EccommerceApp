package com.ecommerce.service;

import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //save product with Category
    public ProductResponseDTO save(ProductRequestDTO productRequestDTO){
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException(
                "Category not found with id: " + productRequestDTO.getCategoryId()));
        Product product = ProductMapper.toEntity(productRequestDTO);
        product.setCategory(category);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    //get all products
    public List<ProductResponseDTO> getAll(){
        return productRepository.findAll()
                .stream().map(product -> ProductMapper.toDTO(product))
                .toList();
    }
}
