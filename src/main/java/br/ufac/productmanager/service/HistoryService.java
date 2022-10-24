package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.model.History;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.repository.HistoryRepository;

@Service
public class HistoryService {

    private final HistoryRepository repo;

    @Autowired
    public HistoryService(HistoryRepository repo){
        this.repo = repo;
    }

    public History getById(Long id) {
        return null;
    }
    
    public List<History> getAll() {
        List<History> histories = repo.findAll();
        return histories;
    }
    
    public List<History> getAllByProduct(Product product) {
        List<History> histories = repo.findAllByProduct(product);
        return histories;
    }
    
    public List<History> getAllBySnapshotDateDesc(Product product) {
        List<History> histories = repo.findAllByProductOrderBySnapshotDateDesc(product);
        return histories;
    }
    
    public List<History> getAllBySnapshotDateAsc(Product product) {
        List<History> histories = repo.findAllByProductOrderBySnapshotDateAsc(product);
        return histories;
    }
    
    @DeleteMapping
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
