package br.ufac.productmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.History;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.service.HistoryService;
import br.ufac.productmanager.service.ProductService;

@RestController
@RequestMapping("/history")
public class HistoryController {
    
    private HistoryService service;
    private ProductService productService;
    
    @Autowired
    public HistoryController(HistoryService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<History>> getAll() {
        List<History> history = service.getAll();
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getById(@PathVariable("id") Long id) {
        
        History history = service.getById(id);
        
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
    
    @GetMapping("search/histories/{id}")
    public ResponseEntity<List<History>> getAllByProduct(@PathVariable("id") Long id){

        Product product = productService.getById(id);
        List<History> histories = service.getAllByProduct(product);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    
    }
    
    @GetMapping("/search/histories/bydate/desc/{id}")
    public ResponseEntity<List<History>> getAllBySnapshotDateDesc(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        List<History> histories = service.getAllBySnapshotDateDesc(product);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
    
    @GetMapping("/search/histories/bydate/asc/{id}")
    public ResponseEntity<List<History>> getAllBySnapshotDateAsc(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        List<History> histories = service.getAllBySnapshotDateAsc(product);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
    
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
