package pl.coderslab.charity.donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.AppUser;

import java.util.List;
import java.util.Optional;

@Service
public interface DonationService {

    List<Donation> findAll();

    Integer getBagsQuantity(List<Donation> list);

    void add(Donation donation);

    void update(Donation donation);

    void delete(Donation donation);

    Optional<List<Donation>> findByInstitution(Institution institution);

    List<Donation> findByUser(AppUser appUser);

    Optional<Donation> findDonationById(Long id);
}
