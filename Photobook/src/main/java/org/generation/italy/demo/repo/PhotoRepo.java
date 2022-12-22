package org.generation.italy.demo.repo;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {
	
	public List<Photo> findByTitleContainingOrTagContaining(String title, String tag);
}
