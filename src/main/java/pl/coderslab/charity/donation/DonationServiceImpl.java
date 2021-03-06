package pl.coderslab.charity.donation;

import org.springframework.stereotype.Repository;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.user.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public Integer getBagsQuantity(List<Donation> list) {
        return list.stream().mapToInt(Donation::getQuantity).sum();
    }

    @Override
    public void add(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public void update(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public void delete(Donation donation) {
        donationRepository.delete(donation);
    }

    @Override
    public Optional<List<Donation>> findByInstitution(Institution institution) {
        return donationRepository.findDonationsByInstitution(institution);
    }

    @Override
    public List<Donation> findByUser(AppUser appUser) {
        return donationRepository.findDonationsByAppUserOrderByPickUpDate(appUser);
    }

    @Override
    public Optional<Donation> findDonationById(Long id) {
        return donationRepository.findById(id);
    }
}
