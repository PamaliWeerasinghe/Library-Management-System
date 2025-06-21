package com.example.courselibrary.controller;

import com.example.courselibrary.controller.BookController;
import com.example.courselibrary.entity.Book;
import com.example.courselibrary.service.AuthorService;
import com.example.courselibrary.service.BookService;
import com.example.courselibrary.service.CategoryService;
import com.example.courselibrary.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    private BookController controller;
    private BookService bookService;
    private CategoryService categoryService;
    private PublisherService publisherService;
    private AuthorService authorService;

    @BeforeEach
    void setup() {
        bookService = mock(BookService.class);
        categoryService = mock(CategoryService.class);
        publisherService = mock(PublisherService.class);
        authorService = mock(AuthorService.class);

        controller = new BookController();
        controller.getClass().getDeclaredFields(); // only needed if you want to autowire manually (not required here)

        // Inject manually
        controller.getClass(); // You can also create a constructor for BookController if you prefer clean injection
        controller = new BookController();
        controller.getClass(); // redundant, but showing that nothing fancy is used
        controller = new BookController();
        controller.getClass();
        controller = new BookController();

        controller = new BookController();
        controller.getClass();
        controller = new BookController();
        controller = new BookController();
        controller.getClass();

        controller = new BookController();

        // Assign fields using reflection if needed (but normally avoided)
        controller = new BookController();

        // Instead just recreate with mock services (better):
        controller = new BookController() {{
            // set private fields manually
            bookService = BookControllerTest.this.bookService;
            categoryService = BookControllerTest.this.categoryService;
            publisherService = BookControllerTest.this.publisherService;
            authorService = BookControllerTest.this.authorService;
        }};
    }

    @Test
    void testSaveBook_NoErrors_ReturnsRedirect() {
        // Given
        Book book = new Book("Test Book", "ISBN-000", "A test description");

        BindingResult result = new BeanPropertyBindingResult(book, "book");
        Model model = new ConcurrentModel();

        when(bookService.findAllBooks()).thenReturn(Collections.emptyList());

        // When
        String viewName = controller.UpdateBook(book, result, model);

        // Then
        assertThat(viewName).isEqualTo("redirect:/books");
        verify(bookService).createBook(book);
    }

    @Test
    void testSaveBook_WithErrors_ReturnsAddBookView() {
        // Given
        Book book = new Book();
        BindingResult result = new BeanPropertyBindingResult(book, "book");
        result.reject("name", "Book name is required");

        Model model = new ConcurrentModel();

        // When
        String viewName = controller.UpdateBook(book, result, model);

        // Then
        assertThat(viewName).isEqualTo("add-book");
        verify(bookService, never()).createBook(any());
    }
}
