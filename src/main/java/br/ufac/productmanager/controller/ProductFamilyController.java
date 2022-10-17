package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.ProductFamily;
import br.ufac.productmanager.service.ProductFamilyService;

@RestController
@RequestMapping("/family")
public class ProductFamilyController implements ICrudController<ProductFamily> {

    private final ProductFamilyService service;

    @Autowired
    public ProductFamilyController(ProductFamilyService service) {
        this.service = service;
    }
    
	@Override
	@GetMapping("/")
	public ResponseEntity<List<ProductFamily>> getAll() {
		List<ProductFamily> families = service.getAll();
		return new ResponseEntity<>(families, HttpStatus.OK);
	}

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductFamily> getById(@PathVariable("id") Long id) {
        ProductFamily register = service.getById(id);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }
    

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<ProductFamily> insert(ProductFamily object) {
    //     ProductFamily register = service.save(object);
    //     return new ResponseEntity<>(register, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<ProductFamily> update(@RequestBody ProductFamily object) {
    //     ProductFamily register = service.save(object);
    //     return new ResponseEntity<>(register, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}