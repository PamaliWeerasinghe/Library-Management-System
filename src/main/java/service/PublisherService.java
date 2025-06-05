package service;

import entity.Author;
import entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(Long id){
        Publisher publisher=publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Publisher Not Found"));
        return publisher;
    }

    public void createPublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id){
        Publisher publisher=publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Publisher Not Found"));
        publisherRepository.deleteById(publisher.getId());
    }

}
