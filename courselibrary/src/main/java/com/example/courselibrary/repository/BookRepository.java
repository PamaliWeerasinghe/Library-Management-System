package com.example.courselibrary.repository;

import com.example.courselibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByPublishers_Id(Long publisherId);
}
