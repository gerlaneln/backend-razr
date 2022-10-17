package br.ufac.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.Team;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByTeam(Team team);
}
