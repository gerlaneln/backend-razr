package br.ufac.productmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.DistribuitionModel;
import br.ufac.productmanager.model.LogDistribuitionModel;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.DistribuitionModelRepository;
import br.ufac.productmanager.repository.LogDistribuitionModelRepository;

@Service
public class DistribuitionModelService implements ICrudService<DistribuitionModel> {

    private final DistribuitionModelRepository repo;
    private final LogDistribuitionModelRepository logRepo;

    @Autowired
    public DistribuitionModelService(DistribuitionModelRepository repo,
    		LogDistribuitionModelRepository logRepo){
        
    	this.repo = repo;
    	this.logRepo = logRepo;
    }
    
    @Override
    public List<DistribuitionModel> getAll(){
        List<DistribuitionModel> models = repo.findAll();
        return models;
    }

    @Override
    public DistribuitionModel getById(Long id) {
        DistribuitionModel register = repo.findById(id).orElse(null);
        return register;
    }

    @Override
    public DistribuitionModel save(DistribuitionModel dist, User user) {
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received DistribuitionModel in DB
        DistribuitionModel savedDist = repo.save(dist);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedDist.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogDistribuitionModel, associated to this DistribuitionModel
        String userName = user.getName();
        LogDistribuitionModel logDist = new LogDistribuitionModel(new Date(instantMilli),
        		logCommentary, savedDist, userName);
        //Save the created LogDistribuitionModel in DB
        logRepo.save(logDist);
        
        //Returns the LogDistribuitionModel saved
        return savedDist;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
