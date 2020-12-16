package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.Institution;

import java.util.List;
import java.util.Optional;

@Service
public interface DonationService {

    List<Donation> findAll();

    Integer getBagsQuantity(List<Donation> list);

    void add(Donation donation);

    void delete(Donation donation);

    Optional<List<Donation>> findByInstitution(Institution institution);
}
