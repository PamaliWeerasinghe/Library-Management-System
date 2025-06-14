package com.example.courselibrary;

import com.example.courselibrary.entity.Author;
import com.example.courselibrary.entity.Book;
import com.example.courselibrary.entity.Category;
import com.example.courselibrary.entity.Publisher;
import com.example.courselibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourselibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourselibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService){
		return (args)->{
			Book book1=new Book("ABC","Book Name","My First Book");
			Author author1=new Author("Test name1","Test Description");
			Category category1=new Category("Science Books");
			Publisher publisher1=new Publisher("First Publisher");
			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

			Book book2=new Book("DEF","Book 2","My Second Book");
			Author author2=new Author("Test name2","Second Description");
			Category category2=new Category("Math Books");
			Publisher publisher2=new Publisher("Second Publisher");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);


		};
	}
}
