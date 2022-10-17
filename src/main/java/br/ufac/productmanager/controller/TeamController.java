package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.Team;
import br.ufac.productmanager.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController implements ICrudController<Team>{

    private final TeamService service;

    @Autowired
    public TeamController(TeamService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Team>> getAll() {
        List<Team> teams = service.getAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Team> getById(@PathVariable("id") Long id) {
        Team team = service.getById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<Team> insert(Team object) {
    //     Team team = service.save(object);
    //     return new ResponseEntity<>(team, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<Team> update(@RequestBody Team object) {
    //     Team team = service.save(object);
    //     return new ResponseEntity<>(team, HttpStatus.OK);
    // }

    @Override
    @DeleteMapping
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
