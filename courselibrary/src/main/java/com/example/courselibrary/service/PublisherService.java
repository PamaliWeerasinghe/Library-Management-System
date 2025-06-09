package com.example.courselibrary.service;

import com.example.courselibrary.entity.Author;
import com.example.courselibrary.entity.Publisher;
import com.example.courselibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(Long id){
        Publisher publisher=publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Author Not Found"));
        return publisher;
    }

    public void createPublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

    public  void deletePublisher(Long id){
        Publisher publisher=publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Author Not Found"));
        publisherRepository.deleteById(publisher.getId());
    }


}
