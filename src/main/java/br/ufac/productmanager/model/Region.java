package br.ufac.productmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Region implements Serializable {
    
	//empty constructor
    public Region(){}

    //constructor
    public Region(String regionName){
        this.regionName = regionName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id", nullable = false, updatable = false)
    private Long id;

    @Column(name="region_name", nullable = false)
    private String regionName;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="region", targetEntity=PhoneCompany.class)
//    private List<PhoneCompany> companies;
//    
//
//    public List<PhoneCompany> getCompanies() {
//        return companies;
//    }
//
//    public void setCompanies(List<PhoneCompany> companies) {
//        this.companies = companies;
//    }
//    
//    public void addCompany(PhoneCompany company) {
//    	this.companies.add(company);
//    }
//    
//    public boolean removeCompany(PhoneCompany company) {
//    	return this.companies.remove(company);
//    }

    public Long getId() {
        return id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
    	return "[\""+getRegionName()+"\","+getId()+"]";
    }
   
    public String toLog(String snapshot){
    	
    	String log = """
			         {
	                     "idRegion":"%s",
	                     "regionName":"%s",
	                     "snapshot":"%s"
			         }
			            """;
    	
        return log.formatted(id, regionName, snapshot);

    }
}
