package br.ufac.productmanager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogTeamFormation;
import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.TeamFormation;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogTeamFormationRepository;
import br.ufac.productmanager.repository.TeamFormationRepository;

@Service
public class TeamFormationService implements ICrudService<TeamFormation>{

    private final TeamFormationRepository repo;
    private final LogTeamFormationRepository logRepo;
    private final TeamService teamService;
    private final UserService userService;

    @Autowired
    public TeamFormationService(TeamFormationRepository repo,
                                LogTeamFormationRepository logRepo,
                                TeamService teamService,
                                UserService userService
                                ){
        this.repo = repo;
        this.logRepo = logRepo;
        this.teamService = teamService;
        this.userService = userService;
    }

    @Override
    public List<TeamFormation> getAll() {
        List<TeamFormation> formatedTeams = repo.findAll();
        return formatedTeams;
    }

    @Override
    public TeamFormation getById(Long id) {
        TeamFormation formatedTeam = repo.findById(id).orElse(null);
        return formatedTeam;
    }

    public List<Team> getByUser(User user){
        
        //Getting the TeamFormation corresponding to the User
        List<TeamFormation> teamFormations = repo.findByUser(user);
        
        List<Team> teamsByUser = new ArrayList<Team>();
        for(TeamFormation t : teamFormations){
            Team team = t.getTeam();
            // XXX DONT MAKE SENSE
            //Adding to the array of Teams of the User
            teamsByUser.add(teamService.getById(team.getId()));
        }
        return teamsByUser;
    }

    public List<User> getByTeam(Team team){
        
        //Getting the TeamFormation corresponding to the Team
        List<TeamFormation> teamFormations = repo.findByTeam(team);

        List<User> usersByTeam = new ArrayList<User>();
        for(TeamFormation t : teamFormations){
            User user = t.getUser();
            //Adding to the array of Users of the Team
            usersByTeam.add(userService.getById(user.getId()));
        }
        return usersByTeam;
    }
    
    public void deleteByUserAndTeam(User user, Team team) {
    	
    	//Getting the TeamFormation that have this user AND team, expected one
    	List<TeamFormation> teamFormations = repo.findByUserAndTeam(user, team);
    	
    	/* TODO XXX 
    	 * Discuss if is necessary to create an verification in the
    	 * insertion of an new TeamFormation to check if an relationship between
    	 * that User and Team already exists */
    	
    	//Getting the first element in the list
    	//This element must be the only in the list, since there is no reason to
    	//create two TeamFormations to represent the same relationship between the 
    	//same User and Team.
    	TeamFormation member = teamFormations.get(0);
    	
    	repo.delete(member);
    }

    @Override
    public TeamFormation save(TeamFormation member, User user) {
    	
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received TeamFormation in DB
    	TeamFormation savedMember = repo.save(member);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedMember.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogTeamFormation, associated to this TeamFormation
        String loggedUserInfo = user.getName();
        String formationInfo = savedMember.getTeam().getName();
        User memberUser = savedMember.getUser();
        
        LogTeamFormation logMember = new LogTeamFormation(new Date(instantMilli),
        		logCommentary, formationInfo, loggedUserInfo, memberUser);
        //Save the created LogTeamFormation in DB
        logRepo.save(logMember);
        
        //Returns the TeamFormation saved
        return savedMember;
    }

    
    public void deleteWithLog(Long id, TeamFormation formation) {
    	
    	/*First, make a log of the action*/
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();
    	
    	User loggedUser = userService.getById(id);
    	
    	//Creates a new LogTeamFormation, associated to this TeamFormation
        String loggedUserInfo = loggedUser.getUsername();
        String formationInfo = formation.getTeam().getName();
        User memberUser = formation.getUser();
        
        /* The JSON commentary, instead of the fields, 
         * will have an array with the string value "Deleted"*/
        String logCommentary = new String("[\"Deleted\"]");
        
        LogTeamFormation logMember = new LogTeamFormation(new Date(instantMilli),
        		logCommentary, formationInfo, loggedUserInfo, memberUser);
        //Save the created LogTeamFormation in DB
        logRepo.save(logMember);
        /*End of the log creation*/
    	
        /*Delete the entity, destroying that relationship between User x Team*/
    	repo.deleteById(id);
    }
    
    @Override
    public void delete(Long id) {
    	repo.deleteById(id);
    }

}
