package pl.coderslab.charity.category;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();

    void add(Category category);
}
