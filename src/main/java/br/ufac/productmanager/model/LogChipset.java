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
@Table(name="chipset_log")
public class LogChipset implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name="log_chipset_id")
	private Long idLogChipset;
	
	@Column(name="snapshot_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", length = 400)
	private String logCommentary;
	
	@ManyToOne
	@JoinColumn(name="chipset_fk")
	private Chipset chipset;
	
	
	@Column(name="user_info")
	private String userInfo;
	
	public LogChipset() {}
	
	public LogChipset(Date snapshotDate, String logCommentary, Chipset chipset, String userInfo) {
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.chipset = chipset;
		this.userInfo = userInfo;
	}

	public Long getIdLogChipset() {
		return idLogChipset;
	}

	public void setIdLogChipset(Long idLogChipset) {
		this.idLogChipset = idLogChipset;
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

	public Chipset getChipset() {
		return chipset;
	}

	public void setChipset(Chipset chipset) {
		this.chipset = chipset;
	}

	public String getUser() {
		return userInfo;
	}

	public void setUser(String user) {
		this.userInfo = user;
	}
	
}
