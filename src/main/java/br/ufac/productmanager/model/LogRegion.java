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
@Table(name="region_log")
public class LogRegion implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_region_id", nullable = false, updatable = false)
	private Long idLogRegion;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="region_fk", updatable = false)
	private Region region;
	
	
	@JoinColumn(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogRegion() {}
	
	//constructor
	public LogRegion(Date snapshotDate, String logCommentary, Region region, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.region = region;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogRegion() {
		return idLogRegion;
	}

	public void setIdLogRegion(Long idLogRegion) {
		this.idLogRegion = idLogRegion;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
}
