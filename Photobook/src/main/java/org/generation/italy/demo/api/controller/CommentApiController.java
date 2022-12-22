package org.generation.italy.demo.api.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/comments")
@CrossOrigin("*")
public class CommentApiController {
	@Autowired
	private PhotoService photoService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/all")
	public List<Comment> getAll() {
		return commentService.findAll();
	}

	@GetMapping("/by/photos/{id}")
	public List<Comment> getCommentsByPhotoId(@PathVariable("id") int id) {
		return photoService.findPhotoById(id).get().getComments();
	}
	
	@PostMapping("/add/{id}")
	public Comment addCommentToPhoto(@PathVariable("id") int id, @Valid @RequestBody Comment comment) {
		System.err.println(comment);
		Photo photo = photoService.findPhotoById(id).get();

		Comment newComment = new Comment(comment.getText(), photo);

		commentService.save(newComment);

		return newComment;
	}
}
