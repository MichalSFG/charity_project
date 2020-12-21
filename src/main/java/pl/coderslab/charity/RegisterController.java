package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.email.ConfirmationToken;
import pl.coderslab.charity.email.ConfirmationTokenRepository;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegisterController {

    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public RegisterController(UserService userService, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService) {
        this.userService = userService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@Valid AppUser appUser, BindingResult result,
                             @RequestParam String password2, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (!appUser.getPassword().equals(password2)) {
            model.addAttribute("password", "Hasło powtórzone niepoprawnie!");
            return "register";
        }

        AppUser byEmail = userService.findByEmail(appUser.getEmail());
        if (byEmail != null) {
            model.addAttribute("email", "Ten email istnieje już w systemie! Wpisz inny!");
            return "register";
        } else {
            userService.saveUser(appUser);

            ConfirmationToken confirmationToken = new ConfirmationToken(appUser);
            confirmationTokenRepository.save(confirmationToken);

            emailService.sendSimpleMessage(appUser.getEmail(), "Complete Registration!",
                    "To confirm your account, please click here : http://localhost:8080/confirm-registration?token="
                            + confirmationToken.getConfirmationToken());

            model.addAttribute("email", appUser.getEmail());
        }

        return "activationLinkInfo";
    }

    @RequestMapping(value = "/confirm-registration", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserRegistration(Model model, @RequestParam("token") String confirmationToken) {

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            AppUser user = userService.findByEmail(token.getAppUser().getEmail());
            user.setEnabled(1);
            userService.updateUser(user);
            model.addAttribute("message", "Congratulations! Your account has been activated and email is verified!");
        } else {
            model.addAttribute("message","The link is invalid or broken!");
        }
        return "successfulRegistration";
    }

}
