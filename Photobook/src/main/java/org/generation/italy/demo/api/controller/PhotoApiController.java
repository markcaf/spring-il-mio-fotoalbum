package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/photo")
@CrossOrigin("*")
public class PhotoApiController {
	
	@Autowired
	private PhotoService photoService;

	@GetMapping("/all")
	public List<Photo> getAll() {
		return photoService.findAll();
	}
	
	@GetMapping("/search/{query}")
	public List<Photo> searchPhotoByTitleOrTag(@PathVariable("query") String query) {
		List<Photo> photos = query == null ? photoService.findAll() : photoService.findByNameOrTag(query);
		return photos;
	}
}
