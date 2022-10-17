package br.ufac.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.ProductScope;


public interface ProductScopeRepository extends JpaRepository<ProductScope, Long>
{
    ProductScope findByProduct(Product product);
}

