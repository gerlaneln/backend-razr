package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogUpgrade;
import br.ufac.productmanager.model.Product;
import br.ufac.productmanager.model.Upgrade;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogUpgradeRepository;
import br.ufac.productmanager.repository.ProductRepository;
import br.ufac.productmanager.repository.UpgradeRepository;

@Service
public class UpgradeService implements ICrudService<Upgrade>{

    private final UpgradeRepository repo;
    private final LogUpgradeRepository logRepo;
    private final ProductRepository productRepo;

    @Autowired
    public UpgradeService(UpgradeRepository repo,
                          LogUpgradeRepository logRepo,
                          ProductRepository productRepo){
        this.repo = repo;
        this.logRepo = logRepo;
        this.productRepo= productRepo;
    }

    @Override
    public List<Upgrade> getAll() {
        List<Upgrade> upgrades = repo.findAll();
        return upgrades;
    }

    @Override
    public Upgrade getById(Long id) {
        Upgrade upgrade = repo.findById(id).orElse(null);
        return upgrade;
    }

    @Override
    public Upgrade save(Upgrade upgrade, User user) {
    	
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received Upgrade in DB
    	Upgrade savedUpgrade = repo.save(upgrade);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedUpgrade.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogUpgrade, associated to this Upgrade
        String userName = user.getName();
        LogUpgrade logUpgrade = new LogUpgrade(new Date(instantMilli),
        		logCommentary, savedUpgrade, userName);
        //Save the created LogUpgrade in DB
        logRepo.save(logUpgrade);
        
        //Returns the Upgrade saved
        return savedUpgrade;
    }

    public Upgrade getByProduct(Long id){
        Product product = productRepo.findById(id).orElse(null);
        return repo.findByProduct(product);
    }

    @Override
    @DeleteMapping
    public void delete(Long id) {
        repo.deleteById(id);
        
    }
    
}
