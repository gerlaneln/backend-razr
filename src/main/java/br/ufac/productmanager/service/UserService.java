package br.ufac.productmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.productmanager.config.TimeUtils;
import br.ufac.productmanager.model.LogUser;
import br.ufac.productmanager.model.User;
import br.ufac.productmanager.repository.LogUserRepository;
import br.ufac.productmanager.repository.UserRepository;

@Service
public class UserService implements ICrudService<User> {

    private final UserRepository repo;
    private final LogUserRepository logRepo;

    @Autowired
    public UserService(UserRepository repo, LogUserRepository logRepo) {
        this.repo = repo;
        this.logRepo = logRepo;
    }

    private User removePassword(User users) {
        users.setPassword(null);
        return users;
    }

    private List<User> removePassword(List<User> users) {
        users.forEach(item -> removePassword(item));
        return users;
    }

    public User getByUsername(String username) {
        User user = repo.findByUsername(username);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> registers = repo.findAll();
        registers = removePassword(registers);
        return registers;
    }

    @Override
    public User getById(Long id) {
        User register = repo.findById(id).orElse(null);
        register = removePassword(register);
        return register;
    }
    
    public User getByIdWithPassword(Long id) {
        User register = repo.findById(id).orElse(null);
        return register;
    }

    // @Override
    // public List<user> getByAll(String termoBusca) {
    //     List<user> registers = repo.findByAll(termoBusca);
    //     registers = removeSenha(registers);
    //     return registers;
    // }

    @Override
    public User save(User object, User user) {
        if (object.getPassword() == null) {
            Long id = object.getId();
            User userToSave = repo.findById(id).orElse(null);
            if (userToSave != null) {
                object.setPassword(userToSave.getPassword(), false);
            }
        }
        
        //Save the received User in DB
        User savedUser = repo.save(object);
        
        if(user != null) {
            /*Log related code*/
            
        	//Get the current instant in milliseconds
        	Long instantMilli = TimeUtils.getLongInstant();
    

    
            //Make a string date from the instant above
        	//Create the log text, with object's fields and date of save
        	String logCommentary = savedUser.toLog(TimeUtils.getStringDateHourFromMilli(instantMilli));
            
        	//Creates a new LogUser, associated to this User
            String userName = user.getName();
            LogUser logUser = new LogUser(new Date(instantMilli),
            		logCommentary, savedUser, userName);
            //Save the created LogUpgrade in DB
            logRepo.save(logUser);
        }

        
        /*Returns the User saved*/
        return savedUser;
    }
    
    /**
     * This method is intended for use only for
     * the first User register in the DB, as it doesn't
     * need a User logged in the system.
     * 
     * For this method work, the line
     * {@code http.authorizeRequests().anyRequest().authenticated();}
     * in Security.java needs to be commented.
     * @param object
     * @return  the User saved
     */
    public User save(User object) {
        if (object.getPassword() == null) {
            Long id = object.getId();
            User userToSave = repo.findById(id).orElse(null);
            if (userToSave != null) {
                object.setPassword(userToSave.getPassword(), false);
            }
        }
        return repo.save(object);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
