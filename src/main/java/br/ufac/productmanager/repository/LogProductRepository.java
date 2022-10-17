package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.LogProduct;

public interface LogProductRepository extends JpaRepository<LogProduct, Long>{

}
