package pe.edu.upeu.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.security.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
