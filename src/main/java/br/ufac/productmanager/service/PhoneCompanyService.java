package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogPhoneCompany;
import br.ufac.productmanager.model.PhoneCompany;
import br.ufac.productmanager.model.Region;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogPhoneCompanyRepository;
import br.ufac.productmanager.repository.PhoneCompanyRepository;
import br.ufac.productmanager.repository.RegionRepository;

@Service
public class PhoneCompanyService implements ICrudService<PhoneCompany>{

    private final PhoneCompanyRepository repo;
    private final LogPhoneCompanyRepository logRepo;
    private final RegionRepository regionRepo;
    @Autowired
    public PhoneCompanyService(PhoneCompanyRepository repo,
                               LogPhoneCompanyRepository logRepo,
                               RegionRepository regionRepo){
        this.repo = repo;
        this.logRepo = logRepo;
        this.regionRepo = regionRepo;
    }
    
    @Override
    public List<PhoneCompany> getAll() {
        List<PhoneCompany> companies = repo.findAll();
        return companies;
    }

    @Override
    public PhoneCompany getById(Long id) {
        PhoneCompany company = repo.findById(id).orElse(null);
        return company;
    }


    @Override
    public PhoneCompany save(PhoneCompany object, User user) {
        
    	//Get the current instant in milliseconds
    	Long instantMilli = TimeUtils.getLongInstant();

        //Save the received PhoneCompany in DB
        PhoneCompany company = repo.save(object);
        
    	//Make a string date from the instant above
    	//Create the log text, with object's fields and date of save
    	String logText = company.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
        
    	//Creates a new LogPhoneCompany, with a PhoneCompany,
        //date and logText.
        String userName = user.getName();
        LogPhoneCompany logCompany = new LogPhoneCompany(company, new Date(instantMilli), logText, userName);
        //Save the created LogPhoneCompany in DB
        logRepo.save(logCompany);
        
        //Returns the PhoneCompany saved
        return company;
    }

    @Override
    @DeleteMapping
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<PhoneCompany> getByRegion(Long idRegion) {

        Region region = regionRepo.findById(idRegion).orElse(null);

        return repo.findByRegion(region);

    }
    
}
