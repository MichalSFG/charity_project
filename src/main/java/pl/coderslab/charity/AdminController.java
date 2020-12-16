package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public AdminController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/home")
    public String adminPage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getAppUser());
        return "admin";
    }

    @RequestMapping("/allInstitutions")
    public String findAllInstitutions(Model model) {
        model.addAttribute("all", institutionService.findAll());
        return "admin/institution/allInstitutions";
    }

    @GetMapping("/addInst")
    public String addInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institution/add";
    }

    @PostMapping("/addInst")
    public String addInstitution(Institution institution) {
        institutionService.add(institution);
        return "redirect:admin/home";
    }

    @GetMapping("/editInst")
    public String editInstitutionForm(@RequestParam Long id, Model model) {
        Institution byId = institutionService.findById(id);
        model.addAttribute("institution", byId);
        return "admin/institution/edit";
    }

    @PostMapping("/editInst")
    public String saveEditedInstitution(Institution institution) {
        institutionService.update(institution);
        return "redirect:admin/home";
    }

    @RequestMapping("/deleteInst")
    public String deleteInstitution(@RequestParam Long id) {
        Institution byId = institutionService.findById(id);
        Optional<List<Donation>> donations = donationService.findByInstitution(byId);
        donations.ifPresent(list -> list.forEach(donationService::delete));
        institutionService.delete(byId);
        return "redirect:allInstitutions";
    }
}
