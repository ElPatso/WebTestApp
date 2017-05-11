package ua.lemekh.webapp.service;

import ua.lemekh.webapp.model.User;
import ua.lemekh.webapp.model.VerificationToken;


public interface UserService {
    void saveVerificationUser(User use);

    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User getCurrentUser();

    User getUser(String verificationToken);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);
}
