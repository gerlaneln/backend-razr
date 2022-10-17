package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LifeCycleStatus;
import br.ufac.productmanager.model.LogLifeCycleStatus;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LifeCycleStatusRepository;
import br.ufac.productmanager.repository.LogLifeCycleStatusRepository;
@Service
public class LifeCycleStatusService implements ICrudService<LifeCycleStatus> {

    private final LifeCycleStatusRepository repo;
    private final LogLifeCycleStatusRepository logRepo;

    public LifeCycleStatusService(LifeCycleStatusRepository repo, LogLifeCycleStatusRepository logRepo){
        this.repo = repo;
        this.logRepo = logRepo;
    }
    @Override
    public List<LifeCycleStatus> getAll() {
        List<LifeCycleStatus> statuses = repo.findAll();
        return statuses;
    }

    @Override
    public LifeCycleStatus getById(Long id) {
        LifeCycleStatus status = repo.findById(id).orElse(null);
        return status;
    }

    @Override
    public LifeCycleStatus save(LifeCycleStatus status, User user) {
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received LifeCycleStatus in DB
        LifeCycleStatus savedStatus = repo.save(status);

        //Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logCommentary = savedStatus.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogLifeCycleStatus, associated to this LifeCycleStatus
        String userName = user.getName();
        LogLifeCycleStatus logStatus = new LogLifeCycleStatus(new Date(instantMilli),
        		logCommentary, savedStatus, userName);
        //Save the created LogLifeCycleStatus in DB
        logRepo.save(logStatus);
        
        //Returns the LifeCycleStatus saved
        return savedStatus;
    }

    @Override
    public void delete(Long id) {
       repo.deleteById(id);
    }
    
}
