package pl.coderslab.charity.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AppUser findByEmail(String email);

    void saveUser(AppUser appUser);

    void saveUserWhenPasswordIsChanged(AppUser appUser, String password);

    void updateUser(AppUser appUser);
}
