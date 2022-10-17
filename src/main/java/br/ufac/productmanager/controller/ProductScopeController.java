package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.ProductScope;
import br.ufac.productmanager.service.ProductScopeService;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/scope")
public class ProductScopeController implements ICrudController<ProductScope>{

    private final ProductScopeService service;

    public ProductScopeController(ProductScopeService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<ProductScope>> getAll() {
        List<ProductScope> scope = service.getAll();
        return new ResponseEntity<>(scope, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductScope> getById(@PathVariable("id") Long id) {
        ProductScope scope = service.getById(id);
        return new ResponseEntity<>(scope, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<ProductScope> insert(ProductScope scopeToSave) {
    //     ProductScope scope = service.save(scopeToSave);
    //     return new ResponseEntity<>(scope, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<ProductScope> update(@RequestBody ProductScope scopeToUpdate) {
    //     ProductScope scope = service.save(scopeToUpdate);
    //     return new ResponseEntity<>(scope, HttpStatus.OK);
    // }

    @GetMapping("/search/product/{id}")
    public ResponseEntity<ProductScope> getByProduct(@PathVariable Long id) {
        ProductScope scope = service.getByProduct(id);
        return new ResponseEntity<>(scope, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
