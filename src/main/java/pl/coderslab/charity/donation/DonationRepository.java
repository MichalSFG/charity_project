package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    Optional<List<Donation>> findDonationsByInstitution(Institution institution);

    List<Donation> findDonationsByAppUserOrderByPickUpDate(AppUser appUser);

}
