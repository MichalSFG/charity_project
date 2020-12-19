package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.user.AppUser;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.UserService;

@Controller
@Slf4j
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        try {
            AppUser byEmail = userService.findByEmail(currentUser.getAppUser().getEmail());
            if (byEmail.getEnabled() == 0) {
                model.addAttribute("denied", "Your account is not active!");
            }
        } catch (NullPointerException e) {
            log.info("user logs in");
        }
        return "login";
    }
}
