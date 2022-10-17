package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.Region;
import br.ufac.productmanager.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController implements ICrudController<Region>{

    private final RegionService service;

    /**
     * Constructor annotated by the {@code @AutoWired} annotation.
     * Get an instance of the {@code RegionService} provided
     * by Spring.
     * 
     * @param service  the {@code RegionService} object
     */
    @Autowired
    public RegionController(RegionService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Region>> getAll() {
        List<Region> regions = service.getAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Region> getById(@PathVariable("id") Long id) {
        Region region = service.getById(id);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<Region> insert(@RequestBody Region regionToSave) {
    //     Region region = service.save(regionToSave);
    //     return new ResponseEntity<>(region, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<Region> update(@RequestBody Region regionToUpdate) {
    //     Region region = service.save(regionToUpdate);
    //     return new ResponseEntity<>(region, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
