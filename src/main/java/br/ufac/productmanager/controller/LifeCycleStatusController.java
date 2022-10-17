package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.LifeCycleStatus;
import br.ufac.productmanager.service.LifeCycleStatusService;
@RestController
@RequestMapping("/status")
public class LifeCycleStatusController implements ICrudController<LifeCycleStatus>{


    private final LifeCycleStatusService service;

    public LifeCycleStatusController(LifeCycleStatusService service){
        this.service= service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<LifeCycleStatus>> getAll() {
      
        List<LifeCycleStatus> statuses = service.getAll();
        return new ResponseEntity<>(statuses,HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<LifeCycleStatus> getById(@PathVariable("id") Long id) {
        LifeCycleStatus status = service.getById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //@Override
    // @PostMapping("/")
    // public ResponseEntity<LifeCycleStatus> insert(@RequestBody LifeCycleStatus statusToSave) {
    //     LifeCycleStatus status = service.save(statusToSave);
    //     return new ResponseEntity<>(status, HttpStatus.CREATED);
    // }

    //@Override
    // @PutMapping("/")
    // public ResponseEntity<LifeCycleStatus> update(@RequestBody LifeCycleStatus statusToUpdate) {
    //     LifeCycleStatus status = service.save(statusToUpdate);
    //     return new ResponseEntity<>(status, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
