package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.LogLifeCycleStatus;

public interface LogLifeCycleStatusRepository extends JpaRepository<LogLifeCycleStatus, Long>{

}
