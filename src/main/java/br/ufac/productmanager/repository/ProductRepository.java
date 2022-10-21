package br.ufac.productmanager.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.Team;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByTeam(Team team);
    List<Product> findAllByFirstSA(LocalDate firstSA);
    List<Product> findAllByFirstSABetween(Date firstSAStart, Date firstSAEnd);
}
