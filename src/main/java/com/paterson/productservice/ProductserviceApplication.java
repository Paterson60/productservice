package com.paterson.productservice;

import com.paterson.productservice.inheritanceexmaple.joinedtable.MentorRepository;
import com.paterson.productservice.inheritanceexmaple.joinedtable.Student;
import com.paterson.productservice.inheritanceexmaple.joinedtable.StudentRepository;
import com.paterson.productservice.inheritanceexmaple.tableperclass.UserRepository;
import com.paterson.productservice.models.Category;
import com.paterson.productservice.models.Product;
import com.paterson.productservice.repositories.CategoryRepository;
import com.paterson.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

	private MentorRepository mentorRepository;

	private StudentRepository studentRepository;

	private CategoryRepository categoryRepository;

	private ProductRepository productRepository;

	public ProductserviceApplication(MentorRepository mentorRepository, StudentRepository studentRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.mentorRepository = mentorRepository;
		this.studentRepository = studentRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Mentor mentor = new Mentor();
//		mentor.setName("John Doe");
//		mentor.setEmail("j@gmail.com");
//		mentor.setAvgRating(100.0);
//		mentorRepository.save(mentor);

//		User user = new User();
//		user.setName("John Doe");
//		user.setEmail("j@gmail");
//		userRepository.save(user);
//
//		Student student = new Student();
//		student.setName("Jane");
//		student.setEmail("jj@gmail");
//		student.setBatch("Batch 1");
//		student.setPsp(100.0);
//		studentRepository.save(student);
//
//		List<User> users = userRepository.findAll();
//		users.forEach(u -> {
//			System.out.println("User: " + u.getName() + ", Email: " + u.getEmail());
//		});

		//joined table
//		Student student = new Student();
//		student.setName("Jane");
//		student.setEmail("jj@gmail");
//		student.setBatch("Batch 1");
//		student.setPsp(100.0);
//		studentRepository.save(student);


//		Category category = new Category();
//		category.setName("Electronics");
//		Category savedCategory = categoryRepository.save(category);
//		Product product = new Product();
//		product.setTitle("Iphone");
//		product.setDescription("smartphone");
//		product.setPrice(1000.0);
//		product.setImage("iphone.jpg");
//		product.setCategory(savedCategory);
//		productRepository.save(product);

		Optional<Category> categoryOptional = categoryRepository.findById(
				UUID.fromString("62ee7b57-217f-4f45-9ec5-c36928c7290a"));

		if (!categoryOptional.isEmpty()){
			Category category = categoryOptional.get();
			System.out.println("Category: " + category.getProducts());
		}
	}
}
