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
@Table(name="phone_company_log")
public class LogPhoneCompany implements Serializable {
    
    public LogPhoneCompany(){}

    public LogPhoneCompany(PhoneCompany company, Date snapshotDate, String logText, String userInfo){

        this.phoneCompany = company;
        this.snapshotDate = snapshotDate;
        this.logText = logText;
        this.userInfo = userInfo;

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_phone_company_id")
    private Long idLogPhoneCompany;

    @ManyToOne
    @JoinColumn(name="phone_company_fk")
    private PhoneCompany phoneCompany;

    @Column(name="user_info")
    private String userInfo;

    @Column(name="snapshot_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date snapshotDate;

    @Column(name="log_text", columnDefinition="TEXT", updatable = false)
    private String logText;

    public Long getIdLogPhoneCompany() {
        return idLogPhoneCompany;
    }

    public void setIdLogPhoneCompany(Long idLogPhoneCompany) {
        this.idLogPhoneCompany = idLogPhoneCompany;
    }

    public PhoneCompany getPhoneCompany() {
        return phoneCompany;
    }

    public void setPhoneCompany(PhoneCompany phoneCompany) {
        this.phoneCompany = phoneCompany;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    
}
