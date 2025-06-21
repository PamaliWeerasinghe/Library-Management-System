package com.example.courselibrary.repository;

import com.example.courselibrary.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {

}
