package ua.lemekh.webapp.service;

import org.springframework.data.domain.Page;
import ua.lemekh.webapp.model.User;
import ua.lemekh.webapp.model.VerificationToken;

import java.util.List;


public interface UserService {
    void saveVerificationUser(User use);



    Page<User> getUsers(Integer pageNumber);

    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User getCurrentUser();

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    void deleteByUsername(String name);
}
