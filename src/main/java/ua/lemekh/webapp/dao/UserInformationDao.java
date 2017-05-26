package ua.lemekh.webapp.dao;

import ua.lemekh.webapp.model.UserInformation;

import java.util.List;

/**
 * Created by Ostap on 16.05.2017.
 */
public interface UserInformationDao {
    void addInformation(UserInformation userInformation);
    List<UserInformation> userInformation();

    UserInformation findById(long id);
}
