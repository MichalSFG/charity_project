package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstitutionService {

    List<Institution> findAll();

    void add(Institution institution);
}
