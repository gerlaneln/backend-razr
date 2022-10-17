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
@Table(name="product_family_log")
public class LogProductFamily implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_family_id", nullable = false, updatable = false)
	private Long idLogFamily;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="family_fk", updatable = false)
	private ProductFamily family;
	

	@Column(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogProductFamily() {}
	
	//constructor
	public LogProductFamily(Date snapshotDate, String logCommentary, ProductFamily family, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.family = family;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogFamily() {
		return idLogFamily;
	}

	public void setIdLogFamily(Long idLogFamily) {
		this.idLogFamily = idLogFamily;
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

	public ProductFamily getFamily() {
		return family;
	}

	public void setFamily(ProductFamily family) {
		this.family = family;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
	
}
