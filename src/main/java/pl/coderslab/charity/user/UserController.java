package pl.coderslab.charity.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
