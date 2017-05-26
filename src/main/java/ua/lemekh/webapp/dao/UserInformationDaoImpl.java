package ua.lemekh.webapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.lemekh.webapp.model.UserInformation;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ostap on 18.05.2017.
 */
@Repository
public class UserInformationDaoImpl implements UserInformationDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addInformation(UserInformation userInformation) {
        Session session = entityManager.unwrap(Session.class);
        session.update(userInformation);

    }

    @Override
    public List<UserInformation> userInformation() {
        return null;
    }

    @Override
    public UserInformation findById(long id) {
        Session session = entityManager.unwrap(Session.class);

        UserInformation userInformation = (UserInformation) session.load(UserInformation.class, new Long(id));
        return userInformation;
    }
}
