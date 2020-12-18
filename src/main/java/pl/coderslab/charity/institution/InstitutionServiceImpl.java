package pl.coderslab.charity.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;


    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public List<Institution> findActivatedInstitutions() {
        return institutionRepository.findAllByActivated();
    }

    @Override
    public Institution findById(Long id) {
        return institutionRepository.findInstById(id);
    }

    @Override
    public void add(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void delete(Institution institution) {
        institutionRepository.delete(institution);
    }
}
