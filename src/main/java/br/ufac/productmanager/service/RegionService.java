package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogRegion;
import br.ufac.productmanager.model.Region;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogRegionRepository;
import br.ufac.productmanager.repository.RegionRepository;

@Service
public class RegionService implements ICrudService<Region>{

    private final RegionRepository repo;
    private final LogRegionRepository logRepo;

    @Autowired
    public RegionService(RegionRepository repo, LogRegionRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }

    /**
     * Get all the {@code Region} objects that are
     * saved in the database.
     * 
     * @return  the {@code List<Region>} containing all regions.
     */
    @Override
    public List<Region> getAll() {
        List<Region> regions = repo.findAll();
        return regions;
    }

    /**
     * Get the region that have an id equals to the
     * id parameter passed.
     * 
     * @param  id  the region id.
     * @return  the {@code Region} object.
     */
    @Override
    public Region getById(Long id) {
        Region region = repo.findById(id).orElse(null);
        return region;
    }

    /**
     * Save the region passed as parameter in the
     * database. Returns an object of the region saved,
     * because the save operation might have changed the
     * object.
     * 
     * @param  region  the region that must be saved.
     * @return  the {@code Region} saved.
     */
    @Override
    public Region save(Region region, User user) {
    	
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received Region in DB
    	Region savedRegion = repo.save(region);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedRegion.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogRegion, associated to this Region
        String userName = user.getName();
        LogRegion logRegion = new LogRegion(new Date(instantMilli),
        		logCommentary, savedRegion, userName);
        //Save the created LogRegion in DB
        logRepo.save(logRegion);
        
        //Returns the Region saved
        return savedRegion;
    }

    /**
     * Delete the region with the same id as the passed
     * id parameter.
     * 
     * @param  id  the id of the region that must be deleted.
     */
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
