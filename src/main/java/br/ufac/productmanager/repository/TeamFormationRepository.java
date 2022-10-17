package br.ufac.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.TeamFormation;
import br.ufac.productmanager.model.User;

public interface TeamFormationRepository extends JpaRepository<TeamFormation, Long>{
    
	List<TeamFormation> findByTeam(Team team);
	
	List<TeamFormation> findByUser(User user);
	
	List<TeamFormation> findByUserAndTeam(User user, Team team);
}
