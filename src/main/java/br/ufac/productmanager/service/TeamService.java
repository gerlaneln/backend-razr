package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogTeam;
import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogTeamRepository;
import br.ufac.productmanager.repository.TeamRepository;

@Service
public class TeamService implements ICrudService<Team>{

    private final TeamRepository repo;
    private final LogTeamRepository logRepo;

    @Autowired
    public TeamService(TeamRepository repo, LogTeamRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teams = repo.findAll();
        return teams;
    }

    @Override
    public Team getById(Long id) {
        Team team = repo.findById(id).orElse(null);
        return team;
    }

    @Override
    public Team save(Team team, User user) {
    	
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received Team in DB
    	Team savedTeam = repo.save(team);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedTeam.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogTeam, associated to this Team
        String userName = user.getName();
        LogTeam logTeam = new LogTeam(new Date(instantMilli),
        		logCommentary, savedTeam, userName);
        //Save the created LogTeam in DB
        logRepo.save(logTeam);
        
        //Returns the Team saved
        return savedTeam;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);

    }
    
}
