package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductFamily implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="family_id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable=false)
    private String nameFamily;
    

    public Long getId(){
        return id;
    }

    public String getNameFamily(){
        return nameFamily;
    }

    public void setNameFamily(String nameFamily){
        this.nameFamily = nameFamily;
    }
    
    @Override
    public String toString() {
    	return "[\""+getNameFamily()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	String log = """
			         {
	                     "idFamily":"%s",
	                     "nameFamily":"%s",
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
    						  nameFamily,
                              snapshot);
    }
}