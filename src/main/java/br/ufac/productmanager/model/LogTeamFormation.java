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
@Table(name="team_formation_log")
public class LogTeamFormation implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_member_id", nullable = false, updatable = false)
	private Long idLogMember;
	
	@Column(name="snapshot_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date snapshotDate;
	
	@Column(name="log_commentary", columnDefinition="TEXT", updatable = false)
	private String logCommentary;
	
	@Column(name="formation_info", updatable=false)
	//Its better to save the Team name here (product name), instead of the TeamFormation id
	private String formationInfo;
	
	@Column(name="logged_user_info", updatable = false)
	private String loggedUserInfo;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="member_user_fk", updatable = false)
	private User memberUser;
	
//	@ManyToOne(optional=true)
//	@JoinColumn(name="member_fk", updatable = true, nullable=true)
//	private TeamFormation member;
	

	//empty constructor
	public LogTeamFormation() {}
	
	//constructor
	public LogTeamFormation(Date snapshotDate, String logCommentary,
			String formationInfo, String loggedUserInfo, User memberUser) {
		
		this.snapshotDate = snapshotDate;
		this.logCommentary = logCommentary;
		this.formationInfo = formationInfo;
		this.loggedUserInfo = loggedUserInfo;
		this.memberUser = memberUser;
	}

	//getters and setters methods
	public Long getIdLogMember() {
		return idLogMember;
	}

	public void setIdLogMember(Long idLogMember) {
		this.idLogMember = idLogMember;
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

	public String getLoggedUser() {
		return loggedUserInfo;
	}

	public void setLoggedUser(String loggedUserInfo) {
		this.loggedUserInfo = loggedUserInfo;
	}
	
	public String getFormationInfo() {
		return formationInfo;
	}

	public void setFormationInfo(String formationInfo) {
		this.formationInfo = formationInfo;
	}

	public User getMemberUser() {
		return memberUser;
	}

	public void setMemberUser(User memberUser) {
		this.memberUser = memberUser;
	}


	
}
