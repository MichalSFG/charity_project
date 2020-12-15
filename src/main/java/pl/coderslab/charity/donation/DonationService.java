package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.Institution;

import java.util.List;

@Service
public interface DonationService {

    List<Donation> findAll();

    Integer getBagsQuantity(List<Donation> list);

    void add(Donation donation);

    List<Donation> findByInstitution(Institution institution);
}
