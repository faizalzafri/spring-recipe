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
import org.springframework.beans.factory.annotation.Autowired;

import com.faizal.springrecipe.converters.RecipeCommandToRecipe;
import com.faizal.springrecipe.converters.RecipeToRecipeCommand;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeRepository recipeRepository;

	RecipeServiceImpl recipeServiceImpl;

	Recipe recipe;

	Set<Recipe> hashSet;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
		recipe = new Recipe();
		recipe.setId(1L);
		hashSet = new HashSet<>();
		hashSet.add(recipe);
	}

	@Test
	public void testGetRecipes() throws Exception {
		when(recipeRepository.findAll()).thenReturn(hashSet);
		assertEquals(recipeServiceImpl.getRecipes().size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	public void testGetRecipeById() throws Exception {
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		assertNotNull(recipeServiceImpl.getRecipeById(1L));
		verify(recipeRepository, times(1)).findById(anyLong());

	}

	@Test
	public void testDeleteById() throws Exception {
		Long id = 1L;

		recipeRepository.deleteById(id);

		verify(recipeRepository).deleteById(anyLong());
	}

}
