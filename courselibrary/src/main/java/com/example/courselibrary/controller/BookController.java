package com.example.courselibrary.controller;

import com.example.courselibrary.entity.Book;
import com.example.courselibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public String findAllBooks(Model model){
        List<Book> books=bookService.findAllBooks();
        model.addAttribute("books",books);
        return "books";
    }
}
