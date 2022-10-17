package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogProductScope;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.ProductScope;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogProductScopeRepository;
import br.ufac.productmanager.repository.ProductRepository;
import br.ufac.productmanager.repository.ProductScopeRepository;

@Service
public class ProductScopeService implements ICrudService<ProductScope>{
    
    private final ProductScopeRepository repo;
    private final LogProductScopeRepository logRepo;
    private final ProductRepository productRepo;
    
    @Autowired
    public ProductScopeService(ProductScopeRepository repo,
                               LogProductScopeRepository logRepo,
                               ProductRepository productRepo){
        this.repo = repo;
        this.logRepo = logRepo;
        this.productRepo= productRepo;
    }
    
    @Override
    public ProductScope getById(Long id){
        ProductScope scope = repo.findById(id).orElse(null);
        return scope;
    }

    @Override
    public List<ProductScope> getAll(){
        List<ProductScope> scope = repo.findAll();
        return scope;
    }
    
    @Override
    public ProductScope save(ProductScope scope, User user) {
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received ProductScope in DB
    	ProductScope savedScope = repo.save(scope);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedScope.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogProductScope, associated to this ProductScope
        String userName = user.getName();
        LogProductScope logScope = new LogProductScope(new Date(instantMilli),
        		logCommentary, savedScope, userName);
        //Save the created LogProductScope in DB
        logRepo.save(logScope);
        
        //Returns the ProductScope saved
        return savedScope;
    }

    public ProductScope getByProduct(Long id) {

        Product product = productRepo.findById(id).orElse(null);

        return repo.findByProduct(product);
    }
    
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
