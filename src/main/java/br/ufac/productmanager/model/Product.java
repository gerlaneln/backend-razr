package br.ufac.productmanager.model;

import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate firstSA;

    @Column(name = "first_ug", nullable=true)
    private LocalDate firstUG;

    @Column(name = "gpd_lead", nullable = false)
    private String gpdLead;

    @Column(name = "npi_lead", nullable = false)
    private String npiLead;

    @JsonIgnore
    @Lob
    @Column(name = "product_photo",columnDefinition = "BLOB", nullable=true)
    private byte[] productPhoto;

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

    public LocalDate getFirstSA() {
        return firstSA;
    }

    public void setFirstSA(LocalDate firstSA) {
        this.firstSA = firstSA;
    }

    public LocalDate getFirstUG() {
        return firstUG;
    }

    public void setFirstUG(LocalDate firstUG) {
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
    
    public String diff(Product next) {
        
        String diff = new String();
        
        //if there is a change in the name, write to the diff string 
        diff = this.name.equals(next.getName()) ?
                "" : "Name changed from %s to %s \n".formatted(name, next.getName());
        
        //if the ODM1 status changed, write to the diff string
        diff = diff.concat(this.isODM1 == next.getIsODM1() ?
                "" : "ODM1 status changed to %s \n".formatted(next.getIsODM1().toString()));      
        //if the firstSA date changed, write to the diff string
        diff = diff.concat(this.firstSA.equals(next.getFirstSA()) ?
                "" : "First SA date changed from %s to %s \n".formatted(firstSA.toString(),
                                                                         next.getFirstSA().toString()));
        
        //if the GPDLead changed, write to the diff string
        diff = diff.concat(this.gpdLead.equals(next.getGpdLead()) ?
                "" : "GPD Lead changed from %s to %s \n".formatted(gpdLead, next.getGpdLead()));
        
        //if the product family changed, write to the diff string
        diff = diff.concat(this.productFamily.equals(next.getProductFamily()) ?
                "" : "Product family changed from %s to %s \n".formatted(productFamily.getNameFamily(),
                                                                          next.getProductFamily().getNameFamily()));
        
        diff = diff.concat(this.chipset != null ?
                //if chipset is other than null,
                //write the changes if they exists
                //or else write nothing if they don't exists.
                this.chipset.equals(next.getChipset()) ?
                        "" : "Chipset changed from %s to %s \n".formatted(chipset.getChipsetName(),
                                                                           next.getChipset().getChipsetName())
                //if chipset is null
                //check if the new version of product have a chipset,
                //if so, write that is the first chipset of this product,
                //else, write nothing.
                            : next.getChipset() != null ?
                                    "Chipset set for first time \n" : " ");
        
        //if the life cycle status changed, write to the diff string
        diff = diff.concat(this.lifeCycleStatus.equals(next.getLifeCycleStatus()) ?
                "" : "Life Cycle status changed from %s to %s \n".
                       formatted(lifeCycleStatus.getName(), next.getLifeCycleStatus().getName()));
        
        return diff;
    }

}
