package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PhoneCompany implements Serializable {

	//empty constructor
    public PhoneCompany(){}

    //constructor
    public PhoneCompany(String companyName, Boolean isMV, Region region){
        this.companyName = companyName;
        this.isMV = isMV;
        this.region = region;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "phone_company_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "is_mv", nullable = false)
    private Boolean isMV = false;

    @ManyToOne(optional = false)
    // @JsonIgnore
    @JoinColumn(name = "region_fk", nullable=false)
    private Region region;


    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getIsMV() {
        return isMV;
    }

    public void setIsMV(Boolean isNV) {
        this.isMV = isNV;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    
    @Override
    public String toString() {
    	return "[\""+getCompanyName()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot){
        
    	String log = """
			         {
	                     "idPhoneCompany":"%s",
	                     "companyName":"%s",
	                     "isNV":"%s",
	                     "region":%s,
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
        					  companyName,
        					  isMV,
        					  region.toString(),
                              snapshot);

    }
}
