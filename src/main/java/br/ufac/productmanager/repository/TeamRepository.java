package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
    
}
