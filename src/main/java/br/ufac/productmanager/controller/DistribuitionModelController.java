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

import br.ufac.productmanager.model.DistribuitionModel;
import br.ufac.productmanager.service.DistribuitionModelService;

@RestController
@RequestMapping("/model")
public class DistribuitionModelController implements ICrudController<DistribuitionModel> {
    private final DistribuitionModelService service;

    @Autowired
    public DistribuitionModelController(DistribuitionModelService service){
        this.service = service;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DistribuitionModel> getById(@PathVariable("id") Long id) {
        DistribuitionModel register = service.getById(id);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<DistribuitionModel>> getAll() {
        List<DistribuitionModel> models = service.getAll();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<DistribuitionModel> insert(DistribuitionModel object) {
    //     DistribuitionModel register = service.save(object);
    //     return new ResponseEntity<>(register, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<DistribuitionModel> update(DistribuitionModel object) {
    //     DistribuitionModel register = service.save(object);
    //     return new ResponseEntity<DistribuitionModel>(register, HttpStatus.OK);
    // }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

