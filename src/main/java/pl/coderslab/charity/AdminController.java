package pl.coderslab.charity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.user.CurrentUser;

@Controller
@Slf4j
public class AdminController {

    @RequestMapping("/admin")
    public String adminPage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("user", currentUser.getAppUser());
        return "admin";
    }
}
