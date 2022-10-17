package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.LifeCycleStatus;

public interface LifeCycleStatusRepository extends JpaRepository<LifeCycleStatus,Long> {
    
}
