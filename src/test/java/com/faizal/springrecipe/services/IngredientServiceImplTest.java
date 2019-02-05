package com.faizal.springrecipe.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faizal.springrecipe.commands.IngredientCommand;
import com.faizal.springrecipe.converters.IngredientToIngredientCommand;
import com.faizal.springrecipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.faizal.springrecipe.domain.Ingredient;
import com.faizal.springrecipe.domain.Recipe;
import com.faizal.springrecipe.repositories.RecipeRepository;

public class IngredientServiceImplTest {

	IngredientToIngredientCommand ingredientToIngredientCommand;

	@Mock
	RecipeRepository recipeRepository;

	IngredientService ingredientService;

	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
				new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
	}

	@Test
	public void getByRecipeIdAndIngredientId() throws Exception {

		Ingredient i1 = new Ingredient();
		i1.setId(1L);
		Ingredient i2 = new Ingredient();
		i1.setId(2L);
		Ingredient i3 = new Ingredient();
		i1.setId(3L);

		Recipe recipe = new Recipe();
		recipe.setId(5L);
		recipe.addIngredient(i1);
		recipe.addIngredient(i2);
		recipe.addIngredient(i3);

		Optional<Recipe> reOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(reOptional);

		IngredientCommand inCommand = ingredientService.getByRecipeIdAndIngredientId(1L, 3L);

		assertEquals(Long.valueOf(3L), inCommand.getId());
		assertEquals(Long.valueOf(5L), inCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyLong());
	}

}
