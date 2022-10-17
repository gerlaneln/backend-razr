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
@Table(name="life_cycle_status_log")
public class LogLifeCycleStatus implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_status_id", nullable = false, updatable = false)
	private Long idLogLifeCycleStatus;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", length = 400, updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="status_fk", updatable = false)
	private LifeCycleStatus lifeStatus;
	
	
	@Column(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogLifeCycleStatus() {}
	
	//constructor
	public LogLifeCycleStatus(Date snapshotDate, String logCommentary, LifeCycleStatus lifeStatus, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.lifeStatus = lifeStatus;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogLifeCycleStatus() {
		return idLogLifeCycleStatus;
	}

	public void setIdLogLifeCycleStatus(Long idLogLifeCycleStatus) {
		this.idLogLifeCycleStatus = idLogLifeCycleStatus;
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

	public LifeCycleStatus getLifeStatus() {
		return lifeStatus;
	}

	public void setLifeStatus(LifeCycleStatus lifeStatus) {
		this.lifeStatus = lifeStatus;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
	
}
