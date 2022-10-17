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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="product_log")
public class LogProduct implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_product_id", nullable = false, updatable = false)
	private Long idLogProduct;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="product_fk", updatable = false)
	private Product product;
	
	
	@Column(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogProduct() {}
	
	//constructor
	public LogProduct(Date snapshotDate, String logCommentary, Product product, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.product = product;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogProduct() {
		return idLogProduct;
	}

	public void setIdLogProduct(Long idLogProduct) {
		this.idLogProduct = idLogProduct;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public String getLogCommentary() {
		return logCommentary;
	}

	public void setLogCommentary(String logCommentary) {
		this.logCommentary = logCommentary;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
	
}
