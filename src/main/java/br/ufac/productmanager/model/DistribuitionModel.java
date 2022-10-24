package br.ufac.productmanager.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DistribuitionModel implements Serializable{
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dist_model_id", nullable = false, updatable=false)
    private Long id;

    @Column(name="model_name", nullable = false)
    private String modelName;

    @Column(nullable = false)
    private Long personnel;

    // @Column(name="ta_date", nullable = false)
    // //@Temporal(TemporalType.DATE)
    // //@JsonFormat(pattern="yyyy-MM-dd")
    // private LocalDate taDate;
    
    @Column(name="before_ta", nullable = false)
    private Integer beforeTa;
    
    @Column(name="after_ta", nullable = false)
    private Integer afterTa;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="region_fk", nullable = false)
    private Region region;
    
	@ManyToOne(optional = true)
	@JoinColumn(name="phone_company_fk", nullable = true)
	private PhoneCompany company;
	

    public Long getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Long personnel) {
        this.personnel = personnel;
    }

    public Long getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    // public LocalDate getTaDate() {
    //     return taDate;
    // }

    // public void setTaDate(LocalDate taDate) {
    //     this.taDate = taDate;
    // }

    public Integer getBeforeTa() {
        return beforeTa;
    }

    public void setBeforeTa(Integer beforeTa) {
        this.beforeTa = beforeTa;
    }

    public Integer getAfterTa() {
        return afterTa;
    }

    public void setAfterTa(Integer afterTa) {
        this.afterTa = afterTa;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    
	public PhoneCompany getCompany() {
		return company;
	}

	public void setCompany(PhoneCompany company) {
		this.company = company;
	}
    
    public String toString() {
    	return "[\""+getModelName()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	String log = """
	                 {
	                     "idModel":"%s",
	                     "modelName":"%s",
	                     "personnel":"%s",
	                     "beforeTa":"%s",
	                     "afterTa":"%s",
	                     "region":%s,
	                     "company":%s,
	                     "snapshot":"%s"
	                 }
				        """;
    			
    			return log.formatted(id,
							  		 modelName,
							  		 personnel,
							  		 beforeTa,
							  		 afterTa,
							  		 region.toString(),
							  		 company != null ? company.toString() : "[Not set, 0]",
							  		 snapshot);
    	
    }

}
