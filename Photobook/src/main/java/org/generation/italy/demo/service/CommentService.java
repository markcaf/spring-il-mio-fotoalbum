package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepo commentRepo;

	public void save(Comment comment) {
		commentRepo.save(comment);
	}

	public List<Comment> findAll() {
		return commentRepo.findAll();
	}

	public Optional<Comment> findCommentById(int id) {
		return commentRepo.findById(id);
	}
}
