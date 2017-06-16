package ua.lemekh.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lemekh.webapp.model.User;
import ua.lemekh.webapp.model.VerificationToken;

/**
 * Created by Ostap on 30.04.2017.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
     VerificationToken findByToken(String token);


}
