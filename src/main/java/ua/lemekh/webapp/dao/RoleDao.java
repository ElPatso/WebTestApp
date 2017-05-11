package ua.lemekh.webapp.dao;

import ua.lemekh.webapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
