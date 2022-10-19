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

import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController implements ICrudController<Product>{
    
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = service.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
        
    	Product product = service.getById(id);
    	
    	// byte[] decompressedImage = service.decompressBytes(product.getProductPhoto());
    	// product.setProductPhoto(decompressedImage);
        
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // @Override
    @GetMapping("/seach/products/{firstDate}/{secondDate}")
    public ResponseEntity<List<Product>> getAllFirstSABetween(@PathVariable("firstDate") String firstDate, @PathVariable("secondDate") String secondDate){

        // String dateOne = firstDate;
        // String dateTwo = secondDate;

        LocalDate fDate = LocalDate.parse(firstDate);
        LocalDate sDate = LocalDate.parse(secondDate);

        List<Product> products = service.getAllByFirstSABetween(fDate, sDate);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<Product> insert(@RequestBody Product productToSave) {
    //     Product product = service.save(productToSave);
    //     return new ResponseEntity<>(product, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<Product> update(@RequestBody Product productToUpdate) {
    //     Product product = service.save(productToUpdate);
    //     return new ResponseEntity<>(product, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<?> delete(Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
