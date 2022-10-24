package br.ufac.productmanager.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class History implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="history_id")
    private Long id;

    @Column(name="snapshot_date")
    private Date snapshotDate;
    
    @Column(columnDefinition="TEXT")
    private String comment;
    
    @Column(name="logged_user")
    private String loggedUser;

    @ManyToOne
    @JoinColumn
    private Product product;
    
    
    public History() {}
    
    public History(Date snapshotDate, String comment, Product product, String loggedUser) {
        this.snapshotDate = snapshotDate;
        this.comment = comment;
        this.product = product;
        this.loggedUser = loggedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String userName) {
        this.loggedUser = userName;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
