package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name="iduser")
    private Long id;

    @Column(nullable = false, unique = true, name="user_name")
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isActive = true; 
    

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(){
        return password;
    }
    
//    public void setFakePassword() {
//        this.password = "no way!!!";
//    }

    public void setPassword(String password) {
        setPassword(password, true);
    }

    public void setPassword(String password, boolean encrypted) {
        if (password != null && encrypted) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            password = passwordEncoder.encode(password);
        }
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
    	return "[\""+getUsername()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	String log = """
			         {
	                     "id":"%s",
	                     "username":"%s",
	                     "name":"%s",
	                     "email":"%s",
	                     "userRole":"%s",
	                     "isActive":"%s",
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
    						  username,
    						  name,
    						  email,
    						  userRole,
    						  String.valueOf(isActive),
                              snapshot);
    }
}
