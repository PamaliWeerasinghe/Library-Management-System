//package com.example.courselibrary.controller;
//
//
//import com.example.courselibrary.service.BookService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookService bookService;
//
//    @Test
//    void testSaveBook_ValidData_ShouldRedirect() throws Exception {
//        mockMvc.perform(post("/save-book")
//                        .param("name", "My Book")
//                        .param("isbn", "ISBN-1234")
//                        .param("description", "A sample description"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/books"));
//
//        verify(bookService, times(1)).createBook(any());
//    }
//
//
//    @Test
//    void testSaveBook_InvalidData_ShouldReturnToForm() throws Exception {
//        mockMvc.perform(post("/save-book")
//                        .param("name", "") // invalid
//                        .param("isbn", "") // invalid
//                        .param("description", "")) // invalid
//                .andExpect(status().isOk())
//                .andExpect(view().name("add-book"));
//
//        verify(bookService, never()).createBook(any());
//    }
//}
