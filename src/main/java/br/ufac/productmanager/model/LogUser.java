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
@Table(name="user_log")
public class LogUser implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_user_id", nullable = false, updatable = false)
	private Long idLogUser;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@ManyToOne()
	@JoinColumn(name="owner_user_fk", updatable = false)
	private User owerUser;
	
	// @ManyToOne()
	@Column(name="logged_user_info", updatable = false)
	private String userInfo;
	
	//empty constructor
	public LogUser() {}
	
	//constructor
	public LogUser(Date snapshotDate, String logCommentary, User owerUser, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.owerUser = owerUser;
		this.userInfo = userInfo;
	}

	//getters and setters methods
	public Long getIdLogUser() {
		return idLogUser;
	}

	public void setIdLogUser(Long idLogUser) {
		this.idLogUser = idLogUser;
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

	public User getOwerUser() {
		return owerUser;
	}

	public void setOwerUser(User owerUser) {
		this.owerUser = owerUser;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}

}
