package com.nike.productservice.service.impl;

import com.nike.productservice.dto.request.CategoryReq;
import com.nike.productservice.dto.response.CategoryRes;
import com.nike.productservice.entity.CategoryEntity;
import com.nike.productservice.repository.CategoryRepository;
import com.nike.productservice.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    @Override
    public CategoryRes save(CategoryReq req) {
        CategoryEntity parentCategory = new CategoryEntity();
        if(req.getParentName() != null && !req.getParentName().isBlank()){
             parentCategory = this.categoryRepository.findCategoryEntityByName(req.getParentName());
            if(parentCategory == null) {
                throw new RuntimeException("Parent category not found: " + req.getParentName());
            }
        }

        boolean isCategoryExist = this.categoryRepository.existsByName(req.getName());
        if(isCategoryExist) {
            throw new RuntimeException("Category named " + req.getName()+ " is existed, please choose another name! ");
        }
        CategoryEntity entity = CategoryEntity.builder()
                .name(req.getName())
                .slug(req.getSlug())
                .description(req.getDescription())
                .parent(parentCategory)
                .build();
        CategoryEntity saved = this.categoryRepository.save(entity);


        return CategoryRes.builder()
                .id(saved.getId())
                .name(saved.getName())
                .slug(saved.getSlug())
                .description(saved.getDescription())
                .parentName(saved.getParent() != null ? saved.getParent().getName() : null)
                .build();
    }
}
