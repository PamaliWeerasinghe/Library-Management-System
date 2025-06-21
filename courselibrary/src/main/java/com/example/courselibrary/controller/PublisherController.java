package com.example.courselibrary.controller;

import com.example.courselibrary.entity.Book;
import com.example.courselibrary.entity.Publisher;
import com.example.courselibrary.service.BookService;
import com.example.courselibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    PublisherService publisherService;

    @Autowired
    BookService bookService;

    @GetMapping("/publishers")
    public String findAllPublishers(Model model){
        List<Publisher> publishers=publisherService.findAllPublishers();
        model.addAttribute("publishers",publishers);
        return "publishers";
    }
    @GetMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Model model){
        Publisher publisher=publisherService.findPublisherById(id);
        model.addAttribute("publisher",publisher);
        return "update-publisher";
    }
    @PostMapping("/update-publisher/{id}")
    public String saveUpdatedPublisher(@PathVariable Long id, Publisher publisher, BindingResult result,Model model){
        if (result.hasErrors())
            return "update-publisher";
        publisherService.updatePublisher(publisher);
        model.addAttribute("publishers",publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/remove-publisher/{id}")
    @Transactional
    public String DeletePublisher(@PathVariable Long id, Model model){
        Publisher publisher=publisherService.findPublisherById(id);

        List<Book> books=bookService.findBooksByPublisher_Id(id);
        for (Book book : books){
            book.getPublishers().remove(publisher);
            bookService.deleteBook(book.getId());
        }

        publisher.getBooks().clear();

        publisherService.deletePublisher(id);

        model.addAttribute("publishers",publisherService.findAllPublishers());
        return "publishers";
    }
}
