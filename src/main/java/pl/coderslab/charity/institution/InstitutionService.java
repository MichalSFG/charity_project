package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstitutionService {

    List<Institution> findAll();

    Institution findById(Long id);

    void add(Institution institution);

    void update(Institution institution);

    void delete(Institution institution);
}
