package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.email.ConfirmationToken;
import pl.coderslab.charity.email.ConfirmationTokenRepository;
import pl.coderslab.charity.email.EmailService;

@Controller
public class UserController {

    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public UserController(UserService userService, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService) {
        this.userService = userService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPasswordForm() {
        return "password/forgotPasswordForm";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String verifyEmail(@RequestParam String email, Model model) {
        AppUser userByEmail = userService.findByEmail(email);
        if (userByEmail == null) {
            return "password/notRegistered";
        }

        ConfirmationToken confirmationToken = new ConfirmationToken(userByEmail);
        confirmationTokenRepository.save(confirmationToken);

        emailService.sendSimpleMessage(userByEmail.getEmail(), "Change your password!", "In order to change your password, please click here: http://localhost:8080/change-your-password?token=" +
                confirmationToken.getConfirmationToken());

        model.addAttribute("email", userByEmail.getEmail());
        return "password/passwordChangingInfo";
    }

    @GetMapping("/change-your-password")
    public String verifyToken(@RequestParam String token, Model model) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (confirmationToken != null) {
            AppUser appUser = userService.findByEmail(confirmationToken.getAppUser().getEmail());
            model.addAttribute("appUser", appUser);
            return "password/newPasswordForm";
        } else {
            model.addAttribute("link", "The link is invalid or broken!");
            return "password/passwordChangingInfo";
        }
    }

    @PostMapping("/saveNewPassword")
    public String saveNewPassword(@RequestParam String email, @RequestParam String pass, @RequestParam String rePassword, Model model) {
        AppUser appUser = userService.findByEmail(email);

        if (!pass.equals(rePassword)) {
            model.addAttribute("appUser", appUser);
            model.addAttribute("incorrectRePassword", "Powtórz hasło poprawnie!");
            return "password/newPasswordForm";
        }

        userService.saveUserWhenPasswordIsChanged(appUser, pass);
        return "password/passwordChanged";
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        AppUser appUser = new AppUser();
        appUser.setFirstName("Jan");
        appUser.setLastName("Nowak");
        appUser.setEmail("user@user");
        appUser.setPassword("user");
        userService.saveUser(appUser);
        return "added";
    }
}
