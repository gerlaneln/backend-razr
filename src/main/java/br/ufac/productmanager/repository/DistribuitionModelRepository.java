package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.DistribuitionModel;


public interface DistribuitionModelRepository extends JpaRepository<DistribuitionModel, Long>{
    
}