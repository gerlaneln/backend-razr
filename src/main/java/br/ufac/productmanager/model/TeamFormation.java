package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeamFormation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_formation_id", nullable = false, updatable = false)
    private Long id;
    
    //TODO XXX Add here column with product name (team name)? Probably not.

    @ManyToOne(optional=false)
    @JoinColumn(name="user_fk", nullable=false)
    private User user;

    @ManyToOne(optional=false)
    @JoinColumn(name="team_fk", nullable=false)
    private Team team;


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
     public String toString() {
     	return "[\""+getTeam().getName()+"\","+getId()+"]";
     }
    
    public String toLog(String snapshot) {
    	
    	String log = """
			         {
	                     "idTeam":"%s",
	                     "user":%s,
	                     "team":%s,
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
    			 	   		  user.toString(),
    			 	   		  team.toString(),
    			 	   		  snapshot);
    }

}
