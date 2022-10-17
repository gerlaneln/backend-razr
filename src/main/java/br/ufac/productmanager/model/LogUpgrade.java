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
@Table(name="upgrade_log")
public class LogUpgrade implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_upgrade_id", nullable = false, updatable = false)
	private Long idLogUpgrade;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="upgrade_fk", updatable = false)
	private Upgrade upgrade;
	
	
	@Column(name="user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogUpgrade() {}
	
	//constructor
	public LogUpgrade(Date snapshotDate, String logCommentary, Upgrade upgrade, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.upgrade = upgrade;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogUpgrade() {
		return idLogUpgrade;
	}

	public void setIdLogUpgrade(Long idLogUpgrade) {
		this.idLogUpgrade = idLogUpgrade;
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

	public Upgrade getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Upgrade upgrade) {
		this.upgrade = upgrade;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}

}
