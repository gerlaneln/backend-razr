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

import br.ufac.productmanager.model.PhoneCompany;
import br.ufac.productmanager.service.PhoneCompanyService;

@RestController
@RequestMapping("/phone_company")
public class PhoneCompanyController implements ICrudController<PhoneCompany>{
    
    private PhoneCompanyService service;

    @Autowired
    public PhoneCompanyController(PhoneCompanyService service){
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<PhoneCompany>> getAll() {
        List<PhoneCompany> companies = service.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PhoneCompany> getById(@PathVariable("id") Long id) {
        PhoneCompany company = service.getById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // @Override
    // @PostMapping("/")
    // public ResponseEntity<PhoneCompany> insert(@RequestBody PhoneCompany companyToSave) {
    //     PhoneCompany company = service.save(companyToSave);
    //     return new ResponseEntity<>(company, HttpStatus.CREATED);
    // }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<PhoneCompany> update(@RequestBody PhoneCompany companyToUpdate) {
    //     PhoneCompany company = service.save(companyToUpdate);
    //     return new ResponseEntity<>(company, HttpStatus.OK);
    // }

    @GetMapping("/search/region/{id}")
    public ResponseEntity<List<PhoneCompany>> getByRegion(@PathVariable("id") Long id) {
        List<PhoneCompany> companies = service.getByRegion(id);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
