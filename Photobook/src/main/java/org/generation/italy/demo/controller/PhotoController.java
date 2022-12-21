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
@RequestMapping("/admin")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/photo")
	public String index(Model model) {
		List<Photo> photos = photoService.findAllWCategory();
		model.addAttribute("photos", photos);
		return "photo-index";
	}
	
	@GetMapping("/photo/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		Optional<Photo> optionalPhoto = photoService.findPhotoById(id);
		if (optionalPhoto.isEmpty()) {
			return "404";
		}

		Photo photo = optionalPhoto.get();
		List<Category> categories = photo.getCategories();

		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);

		return "photo-view";
	}
	
	@GetMapping("/photo/create")
	public String create(Model model) {
		List<Category> categories = categoryService.findAll();

		Photo photo = new Photo();
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);

		return "photo-create";
	}

	@PostMapping("/photo/create")
	public String store(@Valid @ModelAttribute("photo") Photo photo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/admin/photo/create";
		}

		photoService.save(photo);
		return "redirect:/admin/photo";
	}
}
