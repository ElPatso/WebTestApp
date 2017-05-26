package ua.lemekh.webapp.service;

import ua.lemekh.webapp.model.UserInformation;

import java.util.List;

/**
 * Created by Ostap on 18.05.2017.
 */
public interface UserInformationService {
    public void addInformation(UserInformation userInformation);

    List<UserInformation> userInformation();

    UserInformation findById(long id);
}
