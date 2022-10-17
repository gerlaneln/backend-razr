package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.model.TeamFormation;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.service.TeamFormationService;
import br.ufac.productmanager.service.TeamService;
import br.ufac.productmanager.service.UserService;

@RestController
@RequestMapping("/team_formation")
public class TeamFormationController implements ICrudController<TeamFormation>{
    
    private final TeamFormationService service;
    private final TeamService teamService;
    private final UserService userService;

    @Autowired
    public TeamFormationController(TeamFormationService service, TeamService teamService, UserService userService){
        this.service = service;
        this.teamService = teamService;
        this.userService = userService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<TeamFormation>> getAll(){
        List<TeamFormation> formatedTeams = service.getAll();
        return new ResponseEntity<>(formatedTeams, HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<TeamFormation> getById(@PathVariable Long id) {
        TeamFormation formatedTeam = service.getById(id);
        return new ResponseEntity<>(formatedTeam, HttpStatus.OK);
    }

    @GetMapping("/search/user/{id}")
    public ResponseEntity<List<Team>> getByUser(@PathVariable Long id){
        User user = userService.getById(id);
        List<Team> teams = service.getByUser(user);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/search/team/{id}")
    public ResponseEntity<List<User>> getByTeam(@PathVariable Long id){
        Team team = teamService.getById(id);
        List<User> users = service.getByTeam(team);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<TeamFormation> insert(@RequestBody TeamFormation object) {
    //     TeamFormation formatedTeam = service.save(object);
    //     return new ResponseEntity<>(formatedTeam, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<TeamFormation> update(@RequestBody TeamFormation object) {
    //     TeamFormation formatedTeam = service.save(object);
    //     return new ResponseEntity<>(formatedTeam, HttpStatus.OK);
    // }
    
    @Override
    public ResponseEntity<?> delete(Long id) {
    	service.delete(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody TeamFormation formation) {
        service.deleteWithLog(id, formation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{idUser}/team/{idTeam}")
    public ResponseEntity<?> deleteByUserAndTeam(@PathVariable Long idUser, @PathVariable Long idTeam){
        User user = userService.getByIdWithPassword(idUser);
        Team team = teamService.getById(idTeam);
        service.deleteByUserAndTeam(user, team);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
