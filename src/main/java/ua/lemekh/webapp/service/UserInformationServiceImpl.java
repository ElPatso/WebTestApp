package ua.lemekh.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.webapp.dao.UserInformationDao;
import ua.lemekh.webapp.model.UserInformation;

import java.util.List;

/**
 * Created by Ostap on 18.05.2017.
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    UserInformationDao userInformationDao;
    @Transactional
    @Override
    public void addInformation(UserInformation userInformation) {
        userInformationDao.addInformation(userInformation);
    }

}
