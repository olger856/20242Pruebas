package pe.edu.upeu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.entities.Detail;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, String> {
    List<Detail> findBySale_Id(String saleId);
}
