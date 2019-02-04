package com.faizal.springrecipe.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeServiceImpl;

	@Mock
	RecipeRepository recipeRepository;

	Recipe recipe;

	Set<Recipe> hashSet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
		recipe = new Recipe();
		recipe.setId(1L);
		hashSet = new HashSet<>();
		hashSet.add(recipe);
	}

	@Test
	public void getRecipes() throws Exception {
		when(recipeRepository.findAll()).thenReturn(hashSet);
		assertEquals(recipeServiceImpl.getRecipes().size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	public void getRecipeById() throws Exception {
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		assertNotNull(recipeServiceImpl.getRecipeById(1L));
		verify(recipeRepository, times(1)).findById(anyLong());

	}

}
