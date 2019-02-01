package com.faizal.springrecipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faizal.springrecipe.domain.Category;
import com.faizal.springrecipe.domain.UnitOfMeasure;
import com.faizal.springrecipe.repositories.CategoryRepository;
import com.faizal.springrecipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@RequestMapping({ "", "/", "/index", "/index.html" })
	public String getIndexPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		System.out.println("cat : "+categoryOptional.get().getId());
		System.out.println("uom : "+uomOptional.get().getId());
		
		return "index";
	}
}
