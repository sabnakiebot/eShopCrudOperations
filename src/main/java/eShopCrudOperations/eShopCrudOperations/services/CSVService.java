package eShopCrudOperations.eShopCrudOperations.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import eShopCrudOperations.eShopCrudOperations.helper.CSVHelper;
import eShopCrudOperations.eShopCrudOperations.model.News;
import eShopCrudOperations.eShopCrudOperations.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    NewsRepository repository;

    public void save(MultipartFile file) {
        try {
            List<News> tutorials = CSVHelper.csvToNews(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<News> tutorials = repository.findAll();

        ByteArrayInputStream in = CSVHelper.NewsToCSV(tutorials);
        return in;
    }

    public List<News> getAllTutorials() {
        return repository.findAll();
    }
}