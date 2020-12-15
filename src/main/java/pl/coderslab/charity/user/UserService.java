package pl.coderslab.charity.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    AppUser findByEmail(String email);

    void saveUser(AppUser appUser);
}
