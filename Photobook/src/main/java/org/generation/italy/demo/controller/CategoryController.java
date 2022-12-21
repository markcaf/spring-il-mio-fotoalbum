package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String index(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "category-index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category-create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/category/create";
		}

		categoryService.save(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {

		Category category = categoryService.findCategoryById(id).get();
		model.addAttribute("category", category);
		return "category-edit";
	}

	@PostMapping("/edit")
	public String update(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/admin/category/edit/" + category.getId();
		}

		List<Photo> oldCategoryPhotos = categoryService.findCategoryById(category.getId()).get().getPhotos();
		category.setPhotos(oldCategoryPhotos);
		categoryService.save(category);

		return "redirect:/admin/category";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {

		Category category = categoryService.findCategoryById(id).get();
		for (Photo photo : category.getPhotos())
			photo.removeCategory(category);

		categoryService.deleteCategoryById(id);
		return "redirect:/admin/category";
	}
}
