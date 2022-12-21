package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
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
}
