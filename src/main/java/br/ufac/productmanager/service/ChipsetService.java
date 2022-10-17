package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.Chipset;
import br.ufac.productmanager.model.LogChipset;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.ChipsetRepository;
import br.ufac.productmanager.repository.LogChipsetRepository;

@Service
public class ChipsetService implements ICrudService<Chipset>{

    private final ChipsetRepository repo;
    private final LogChipsetRepository logRepo;

    @Autowired
    public ChipsetService(ChipsetRepository repo, LogChipsetRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }

    
    @Override
    public List<Chipset> getAll() {
        List<Chipset> chips = repo.findAll();
        return chips;
    }

    
    @Override
    public Chipset getById(Long id) {
        Chipset chips = repo.findById(id).orElse(null);
        return chips;
    }

    @Override
    public Chipset save(Chipset chip, User user) {
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received Chipset in DB
        Chipset savedChip = repo.save(chip);

        //Make a string date from the instant above and pass to the toLog method in chip
    	//Create the log text, with object's fields and date of save
    	String logText = savedChip.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogChipset, associated to this chipset
        String userName = user.getName();
        LogChipset logChip = new LogChipset(new Date(instantMilli), logText, savedChip, userName);
        //Save the created LogChipset in DB
        logRepo.save(logChip);
        
        //Returns the Chipset saved
        return savedChip;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
