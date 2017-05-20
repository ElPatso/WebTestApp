package ua.lemekh.webapp.service;

import ua.lemekh.webapp.dao.RoleDao;
import ua.lemekh.webapp.dao.UserDao;
import ua.lemekh.webapp.dao.VerificationTokenRepository;
import ua.lemekh.webapp.model.Role;
import ua.lemekh.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.webapp.model.UserInformation;
import ua.lemekh.webapp.model.VerificationToken;

import java.util.HashSet;
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
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
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
}
