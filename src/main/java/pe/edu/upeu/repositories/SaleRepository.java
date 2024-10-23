package pe.edu.upeu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.entities.Sale;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, String> {
    List<Sale> findByClient_UserName(String userName);
}
