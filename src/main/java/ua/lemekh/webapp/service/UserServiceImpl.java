package ua.lemekh.webapp.service;

import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.lemekh.webapp.dao.RoleDao;
import ua.lemekh.webapp.dao.UserDao;
import ua.lemekh.webapp.dao.VerificationTokenRepository;
import ua.lemekh.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);

        userDao.save(user);

    }

    @Transactional
    public void saveVerificationUser(User user){
        userDao.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageNumber) {
        PageRequest request =
                new PageRequest(pageNumber - 1, 1);
        return userDao.findAll(request);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }



    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username="";
        Object object = authentication.getPrincipal();
        if (object instanceof UserDetails){
            username = ((UserDetails) object).getUsername();
        }
          return userDao.findByUsername(username);

    }


    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Transactional
    @Override
    public void deleteByUsername(String name) {
        userDao.deleteByUsername(name);
    }
}
