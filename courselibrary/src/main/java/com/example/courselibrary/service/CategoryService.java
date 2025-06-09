package com.example.courselibrary.service;

import com.example.courselibrary.entity.Category;
import com.example.courselibrary.entity.Publisher;
import com.example.courselibrary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories(){
        return  categoryRepository.findAll();
    }

    public Category findCategoryById(Long id){
        Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category Not Found"));
        return category;
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category Not Found"));
        categoryRepository.deleteById((category.getId()));
    }
}
