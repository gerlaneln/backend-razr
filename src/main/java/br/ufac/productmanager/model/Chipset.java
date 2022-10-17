package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Chipset implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chipset_id", nullable = false, updatable = false)
    private Long id;
    
    @Column(nullable=false)
    private String manufacturerName;

    @Column(nullable=false)
    private String chipsetName;
    

    public Long getId() {
        return id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getChipsetName() {
        return chipsetName;
    }

    public void setChipsetName(String chipsetName) {
        this.chipsetName = chipsetName;
    }
    
    @Override
    public String toString() {
    	return "[\""+getChipsetName()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	   
    	String log = """
			         {
			             "idchip":"%s",
			             "manufacturerName":"%s",
			             "chipsetName":"%s",
			             "snapshot":"%s"
			         }
				        """;
    	return log.formatted(id,
				  			 manufacturerName,
				  			 chipsetName,
				  			 snapshot);
            
    }


}
