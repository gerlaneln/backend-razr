package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.Upgrade;
import br.ufac.productmanager.service.UpgradeService;

@RestController
@RequestMapping("/upgrade")
public class UpgradeController implements ICrudController<Upgrade>{

    private final UpgradeService service;

    @Autowired
    public UpgradeController(UpgradeService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Upgrade>> getAll() {
        List<Upgrade> upgrades = service.getAll();
        return new ResponseEntity<>(upgrades, HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Upgrade> getById(@PathVariable("id") Long id) {
        Upgrade upgrade = service.getById(id);
        return new ResponseEntity<>(upgrade, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<Upgrade> insert(@RequestBody Upgrade object) {
    //     Upgrade upgrade = service.save(object);
    //     return new ResponseEntity<>(upgrade, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<Upgrade> update(@RequestBody Upgrade objeto) {
    //     Upgrade upgrade = service.save(objeto);
    //     return new ResponseEntity<>(upgrade, HttpStatus.OK);
    // }

    @GetMapping("/search/product/{id}")
    public ResponseEntity<Upgrade> getByProduct(@PathVariable Long id){
        Upgrade upgrade = service.getByProduct(id);
        return new ResponseEntity<>(upgrade, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
