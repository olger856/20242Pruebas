package pe.edu.upeu.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.security.entities.Role;
import pe.edu.upeu.security.enums.RoleList;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);
}
