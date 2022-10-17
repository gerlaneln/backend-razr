package br.ufac.productmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class ProductScope  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scope_id", nullable = false, updatable = false)
    private Long id;
    
    @Column(nullable=true)
    private String comment;

    @Column(nullable=false)
    private String so;

    @OneToOne(optional=false)
    @JoinColumn(name="product_fk", nullable=false, updatable=true)
    private Product product;

    // XXX
    @ManyToOne(optional=false)
    @JoinColumn(name="dist_model_fk", nullable=false)
    private DistribuitionModel distModel;


    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentary() {
        return comment;
    }

    public void setCommentary(String comment) {
        this.comment = comment;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DistribuitionModel getDistModel() {
        return distModel;
    }

    public void setDistModel(DistribuitionModel distModel) {
        this.distModel = distModel;
    }

    public String toString() {
    	return "[\""+getCommentary()+"\","+getId()+"]";
    }
    
    public String toLog(String snapshot) {
    	
    	String log = """
			         {
	                     "idscope":"%s",
	                     "comment":"%s",
	                     "so":"%s",
	                     "product":%s,
	                     "distModel":%s,
	                     "snapshot":"%s"
			         }
			            """;
    	
    	return  log.formatted(id,
    						  comment,
    						  so,
    						  product.toString(),
                              distModel.toString(),
                              snapshot);
    }

}
