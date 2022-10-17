package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.Chipset;
import br.ufac.productmanager.service.ChipsetService;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/chipset")

public class ChipsetController implements ICrudController<Chipset>{

    private final ChipsetService service;

    public ChipsetController(ChipsetService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Chipset>> getAll() {
        List<Chipset> chips = service.getAll();
        return new ResponseEntity<>(chips, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Chipset> getById(@PathVariable("id") Long id) {
        Chipset chips = service.getById(id);
        return new ResponseEntity<>(chips, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<Chipset> insert(@RequestBody Chipset chipsToSave) {
    //     Chipset chips = service.save(chipsToSave);
    //     return new ResponseEntity<>(chips, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<Chipset> update(@RequestBody Chipset chipsToUpdate) {
    //     Chipset chips = service.save(chipsToUpdate);
    //     return new ResponseEntity<>(chips, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
