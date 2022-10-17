package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.ProductFamily;


public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Long>{
    
}