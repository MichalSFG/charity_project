package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Donation> donationList = donationService.findAll();
        model.addAttribute("donations", donationList.size());
        model.addAttribute("bags", donationService.getBagsQuantity(donationList));
        return "index";
    }

    @ModelAttribute("evenInst")
    public List<Institution> evenInstitutions() {
        List<Institution> institutions = institutionService.findAll();
        List<Institution> even = new ArrayList<>();
        for (int i = 0; i < institutions.size(); i++) {
            if (i % 2 == 0) {
                even.add(institutions.get(i));
            }
        }
        return even;
    }

    @ModelAttribute("oddInst")
    public List<Institution> oddInstitutions() {
        List<Institution> institutions = institutionService.findAll();
        List<Institution> odd = new ArrayList<>();
        for (int i = 0; i < institutions.size(); i++) {
            if (i % 2 != 0) {
                odd.add(institutions.get(i));
            }
        }
        return odd;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInstitution() {
        Institution institution = new Institution();
        institution.setName("Fundacja “Bez domu”");
        institution.setDescription("Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania");
        institutionService.add(institution);
        return "added";
    }

}
