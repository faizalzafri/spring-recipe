package com.faizal.springrecipe.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}