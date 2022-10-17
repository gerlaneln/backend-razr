package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.Upgrade;

public interface UpgradeRepository extends JpaRepository<Upgrade, Long>{

    Upgrade findByProduct(Product product);
    
}
