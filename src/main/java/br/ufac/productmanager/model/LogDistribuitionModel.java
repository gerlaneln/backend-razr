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
@Table(name="distribution_model_log")
public class LogDistribuitionModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_dist_model_id", nullable = false, updatable = false)
	private Long idDistributionModel;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="dist_model_fk", updatable = false)
	private DistribuitionModel distModel;
	
	
	@Column(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogDistribuitionModel() {}
	
	//constructor
	public LogDistribuitionModel(Date snapshotDate, String logCommentary,
			DistribuitionModel distModel, String userInfo) {
		
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.distModel = distModel;
		this.userInfo = userInfo;
	}

	public Long getIdDistributionModel() {
		return idDistributionModel;
	}

	public void setIdDistributionModel(Long idDistributionModel) {
		this.idDistributionModel = idDistributionModel;
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

	public DistribuitionModel getDistModel() {
		return distModel;
	}

	public void setDistModel(DistribuitionModel distModel) {
		this.distModel = distModel;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
	
}
