package ua.lemekh.webapp.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lemekh.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.lemekh.webapp.validator.Pagination;

import java.awt.print.Pageable;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    void deleteByUsername(String name);
}
