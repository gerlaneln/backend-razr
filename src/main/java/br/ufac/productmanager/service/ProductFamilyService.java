package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogProductFamily;
import br.ufac.productmanager.model.ProductFamily;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogProductFamilyRepository;
import br.ufac.productmanager.repository.ProductFamilyRepository;

@Service
public class ProductFamilyService implements ICrudService<ProductFamily>{

    private final ProductFamilyRepository repo;
    private final LogProductFamilyRepository logRepo;

    @Autowired
    public ProductFamilyService(ProductFamilyRepository repo, LogProductFamilyRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }

    @Override
    public List<ProductFamily> getAll(){
        List<ProductFamily> families = repo.findAll();
        return families;
    }
    @Override
    public ProductFamily getById(Long id) {
        ProductFamily register = repo.findById(id).orElse(null);
        return register;
    }
    @Override
    public ProductFamily save(ProductFamily family, User user){
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received ProductFamily in DB
    	ProductFamily savedFamily = repo.save(family);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedFamily.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogProductFamily, associated to this ProductFamily
        String userName = user.getName();
        LogProductFamily logFamily = new LogProductFamily(new Date(instantMilli),
        		logCommentary, savedFamily, userName);
        //Save the created LogProductFamily in DB
        logRepo.save(logFamily);
        
        //Returns the ProductFamily saved
        return savedFamily;
    }
    @Override
    public void delete(Long id){
        repo.deleteById(id);
    }

}