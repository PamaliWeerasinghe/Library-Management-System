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

import java.lang.reflect.Field;
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
    void setup() throws Exception {
        controller = new BookController();

        // Create mocks
        bookService = mock(BookService.class);
        categoryService = mock(CategoryService.class);
        publisherService = mock(PublisherService.class);
        authorService = mock(AuthorService.class);

        // Inject mocks using reflection
        injectPrivateField(controller, "bookService", bookService);
        injectPrivateField(controller, "categoryService", categoryService);
        injectPrivateField(controller, "publisherService", publisherService);
        injectPrivateField(controller, "authorService", authorService);
    }

    private void injectPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
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
