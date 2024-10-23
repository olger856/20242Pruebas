package pe.edu.upeu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product ,String> {
    List<Product> findByCategoryAndIdNot(String category, String ProductId);
    List<Product> findFirst4ByOrderByPriceAsc();
}
