package br.ufac.productmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.History;
import br.ufac.productmanager.model.Product;

public interface HistoryRepository extends JpaRepository<History, Long>{
    
    List<History> findAllByProduct(Product product);
    List<History> findAllByProductOrderBySnapshotDateDesc(Product product);
    List<History> findAllByProductOrderBySnapshotDateAsc(Product product);
    
}
