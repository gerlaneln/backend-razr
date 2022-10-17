package br.ufac.productmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Upgrade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="upgrade_id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable=false)
    private String upgradeType;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    // @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;

    @Column(nullable=true)
    private String changeLog;

    @ManyToOne(optional=false)
    @JoinColumn(name="product_fk")
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setIdUpgrade(Long idUpgrade) {
        this.id = idUpgrade;
    }

    public String getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(String upgradeType) {
        this.upgradeType = upgradeType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
    
    public String toString() {
    	return "[\""+getUpgradeType()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
		
    	String log = """
			         {
	                     "idUpgrade":"%s",
	                     "upgradeType":"%s",
	                     "releaseDate":"%s",
	                     "changeLog":"%s",
	                     "product":%s,
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
							  upgradeType,
							  releaseDate,
							  changeLog,
							  product.toString(),
                              snapshot);
    	
    }

}
