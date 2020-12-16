package pl.coderslab.charity.donation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.Role;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class DonationController {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    private Donation newDonation;

    public DonationController(DonationService donationService, InstitutionService institutionService, CategoryService categoryService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }

    @GetMapping("/home")
    public String form(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<String> roles = currentUser.getAppUser().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:admin/home";
        }

        model.addAttribute("donation", new Donation());
        model.addAttribute("user", currentUser.getAppUser());
        return "form";
    }

    @PostMapping("/home")
    public String createDonation(@Valid Donation donation, BindingResult result,
                                 @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        donation.setAppUser(currentUser.getAppUser());
        donationService.add(donation);
        model.addAttribute("user", currentUser.getAppUser());
        return "confirmation";
    }

    @GetMapping("/form2")
    public String form2(Model model) {
        model.addAttribute("donation", new Donation());
        return "form2";
    }

    @PostMapping("/form2")
    public String processForm2(@Valid Donation donation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.info("ERRORS");
            return "form2";
        }
        log.info("OK! OK! OK!");
        newDonation = donation;
        model.addAttribute("don", donation);
        return "summary";
    }

    @RequestMapping("/save")
    public String saveDonation() {
        donationService.add(newDonation);
        return "confirmation";
    }

    @RequestMapping("/addDonation")
    @ResponseBody
    public String addDonation() {
        Donation donation = new Donation();
        Category category = new Category();
        category.setId(2L);
        Category category2 = new Category();
        category2.setId(4L);
        List<Category> list = new ArrayList<>();
        list.add(category);
        list.add(category2);
        donation.setCategories(list);
        Institution institution = new Institution();
        institution.setId(2L);
        donation.setInstitution(institution);
        donation.setQuantity(2);
        donation.setCity("Łomianki");
        donation.setStreet("Mickiewicza");
        donation.setZipCode("15-987");
        donation.setPickUpDate(LocalDate.now());
        donation.setPickUpTime(LocalTime.now());
        donation.setPickUpComment("2 ciężkie worki");
        donationService.add(donation);
        return "donation added";
    }

}
