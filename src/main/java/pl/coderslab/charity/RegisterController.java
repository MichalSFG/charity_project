package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
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
        }

        userService.saveUser(appUser);
        return "redirect:login";
    }
}
