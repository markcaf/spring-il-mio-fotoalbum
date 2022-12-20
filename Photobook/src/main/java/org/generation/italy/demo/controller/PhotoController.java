package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
