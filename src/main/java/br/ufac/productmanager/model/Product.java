package br.ufac.productmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable=false)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String broadband;

    @Column(name = "is_odm1", nullable = false)
    private Boolean isODM1 = false;

    @Column(name = "first_sa", nullable = false)
    @Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstSA;

    @Column(name = "first_ug", nullable=true)
    @Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstUG;

    @Column(name = "gpd_lead", nullable = false)
    private String gpdLead;

    @Column(name = "npi_lead", nullable = false)
    private String npiLead;

    @JsonIgnore
    @Lob
    @Column(name = "product_photo",columnDefinition = "BLOB", nullable=true)
    private byte[] productPhoto;

    // XXX
    // @OneToOne(optional = false, mappedBy = "product")
    // private ProductScope productScope;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_family_fk", nullable=false)
    private ProductFamily productFamily;

    @ManyToOne(optional = true)
    @JoinColumn(name = "chipset_fk", nullable=true)
    private Chipset chipset;

    // TODO test
    @ManyToOne(optional = false)
    @JoinColumn(name = "life_cicle_status_fk", nullable=false)
    private LifeCycleStatus lifeCycleStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_fk", nullable=false)
    private Team team;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBroadband() {
        return broadband;
    }

    public void setBroadband(String broadband) {
        this.broadband = broadband;
    }

    public Boolean getIsODM1() {
        return isODM1;
    }

    public void setIsODM1(Boolean isODM1) {
        this.isODM1 = isODM1;
    }

    public Date getFirstSA() {
        return firstSA;
    }

    public void setFirstSA(Date firstSA) {
        this.firstSA = firstSA;
    }

    public Date getFirstUG() {
        return firstUG;
    }

    public void setFirstUG(Date firstUG) {
        this.firstUG = firstUG;
    }

    public String getGpdLead() {
        return gpdLead;
    }

    public void setGpdLead(String gpdLead) {
        this.gpdLead = gpdLead;
    }

    public String getNpiLead() {
        return npiLead;
    }

    public void setNpiLead(String npiLead) {
        this.npiLead = npiLead;
    }
    
    public byte[] getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(byte[] productPhoto) {
        this.productPhoto = productPhoto;
    }

    public ProductFamily getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public Chipset getChipset() {
        return chipset;
    }

    public void setChipset(Chipset chipset) {
        this.chipset = chipset;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    

    public LifeCycleStatus getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(LifeCycleStatus lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    // // XXX
    // public ProductScope getProductScope() {
    //     return productScope;
    // }

    // // XXX
    // public void setProductScope(ProductScope productScope) {
    //     this.productScope = productScope;
    // }

    @Override
    public String toString() {
        return "[\""+getName()+"\","+getId()+"]";
    }

    public String toLog(String snapshot) {

    	String log = """
			         {
	                     "idProduct":"%s",
	                     "name":"%s",
	                     "broadband":"%s",
	                     "isODM1":"%s",
	                     "firstSA":"%s",
	                     "firstUG":"%s",
	                     "gpdLead":"%s",
	                     "npiLead":"%s",
	                     "productFamily":%s,
	                     "chipset":%s,
	                     "lifeCycleStatus":%s,
	                     "team":%s,
	                     "snapshot":"%s"
			         }
			            """;
    	
    	
        return log.formatted(id,
            		   		 name,
            		   		 broadband,
            		   		 isODM1,
            		   		 firstSA.toString(),
            		   		 firstUG != null ? firstUG.toString() : "Not set",
            		   		 gpdLead,
            		   		 npiLead,
            		   		//  productScope.toString(),
            		   		 productFamily.toString(),
            		   		 chipset != null ? chipset.toString() : "Not set",
            		   		 lifeCycleStatus.toString(),
            		   		 team.toString(),
            		   		 snapshot);
    }

    public String toHistory(String snapshot) {
        
        String history = """
                         {
                            "name":"%s",
                            "first"
                         }
                            """;



        return history;
    }

}
