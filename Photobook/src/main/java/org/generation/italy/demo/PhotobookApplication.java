package org.generation.italy.demo;

import java.util.ArrayList;
import java.util.List;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.service.CategoryService;
import org.generation.italy.demo.service.CommentService;
import org.generation.italy.demo.service.PhotoService;
import org.generation.italy.demo.service.RoleService;
import org.generation.italy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotobookApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	public static void main(String[] args) {
		SpringApplication.run(PhotobookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//ADDING CATEGORIES
		
		Category art = new Category("Art");
		Category wallpaper = new Category("Wallpaper");
		Category comic = new Category("Comic");
		Category undefined = new Category("Undefined");
		
		List<Category> artWallpaper = new ArrayList<>();
		artWallpaper.add(art);
		artWallpaper.add(wallpaper);
		
		List<Category> comicWallpaper = new ArrayList<>();
		comicWallpaper.add(comic);
		comicWallpaper.add(wallpaper);
		
		List<Category> undefinedList = new ArrayList<>();
		undefinedList.add(undefined);
		
		categoryService.save(art);
		categoryService.save(wallpaper);
		categoryService.save(comic);
		categoryService.save(undefined);
		
		
		// ADDING PHOTOS
		
		Photo p1 = new Photo("Jimi Hendrix", "Guitar art music Painting Wall", "https://i.pinimg.com/564x/77/01/8c/77018cb58dbe090629b29ec522e88033.jpg", "music", true, artWallpaper);
		Photo p2 = new Photo("Cowboy Bebop", "Took the popular Spike \"Bang\" phone wallpaper and touched it up a bit", "https://i.pinimg.com/564x/19/0d/62/190d62d4ddb3b0dc36ce5a78e0d9da09.jpg", "anime", true, comicWallpaper);
		Photo p3 = new Photo("Comic Art", "Cool comic art on twitter", "https://i.pinimg.com/564x/88/e6/ef/88e6ef7ec6ae3ba421e87cf4bece891a.jpg", "comic", true, comicWallpaper);
		Photo p4 = new Photo("Comic Cyberpunk", "Cyberpunk comic style on twitter", "https://i.pinimg.com/564x/df/95/4f/df954fb7957a9e3b23f0efa22f5e0dab.jpg", "comic", true, comicWallpaper);
		Photo p5 = new Photo("Wallpaper Walking", "Walk on skyline artistic wallpaper", "https://i.pinimg.com/564x/bd/8a/c3/bd8ac396f7e6d703bc3b9addc690b6f8.jpg", "walk", true, artWallpaper);
		Photo p6 = new Photo("Land of Sin", "Science fiction art in apocalypse future", "https://i.pinimg.com/564x/05/f1/00/05f100fa3026d62ddfd312b038415ee8.jpg", "scifiart", true, undefinedList);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		photoService.save(p5);
		photoService.save(p6);
		
		System.err.println(photoService.findAllWCategoryAndComment());
		
		Role admin = new Role("ADMIN");
		roleService.save(admin);
		User admin1 = new User("admin", "{noop}admin", admin);
		userService.save(admin1);
		
		Comment comment1 = new Comment("the best guitarist!", p1);
		Comment comment2 = new Comment("really high my friend", p5);
		Comment comment3 = new Comment("awesome", p2);
		Comment comment4 = new Comment("true, it's cool", p3);
		Comment comment5 = new Comment("true, it's very cool", p4);

		commentService.save(comment1);
		commentService.save(comment2);
		commentService.save(comment3);
		commentService.save(comment4);
		commentService.save(comment5);
	}
	
	
}
