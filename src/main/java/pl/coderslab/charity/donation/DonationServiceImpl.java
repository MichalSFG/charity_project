package pl.coderslab.charity.donation;

import org.springframework.stereotype.Repository;
import pl.coderslab.charity.institution.Institution;

import java.util.List;

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
    public List<Donation> findByInstitution(Institution institution) {
        return donationRepository.findDonationsByInstitution(institution);
    }

    @Override
    public Integer getBagsQuantity(List<Donation> list) {
        return list.stream().mapToInt(Donation::getQuantity).sum();
    }

    @Override
    public void add(Donation donation) {
        donationRepository.save(donation);
    }
}
