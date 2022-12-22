package org.generation.italy.demo.api.controller;


import java.util.List;
import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/category")
@CrossOrigin("*")
public class CategoryApiController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/by/photo/{id}")
	public List<Category> getCategoriesByPhotoId(@PathVariable("id") int id) {
		return photoService.findPhotoById(id).get().getCategories();
	}
}
