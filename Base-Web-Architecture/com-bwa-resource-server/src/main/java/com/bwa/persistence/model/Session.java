package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ITOB_SESSIONS")
@AttributeOverride(name="id", column=@Column(name="STR_SESSION_ID", length=80))
public class Session extends IdEntry<String> {

    @Basic(optional = false)
    @Column(name = "ID_CUSTOMER" , nullable = false)
    private Long customerId;

    @Basic(optional = false)
    @Column(name = "STR_IDENTIFICATION" , nullable = false)
    private String userId;

    @Basic(optional = false)
    @Column(name = "STR_ORIGIN" , nullable = false)
    private String origin;

    @Basic(optional=false)
    @Column(name="DAT_LOGON", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date datLogon;

    @Basic(optional=true)
    @Column(name="DAT_LOGOFF", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date datLogOff;

    @Basic(optional=true)
    @Column(name="DAT_LAST_ACTIVITY", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date datLastActivity;

    public boolean isSetCustomerId() {
        return customerId != null;
    }

    public boolean isSetUserId() {
        return userId != null;
    }

    public boolean isSetOrigin() {
        return origin != null;
    }

    public boolean isSetDatLogon() {
        return datLogon != null;
    }

    public boolean isSetDatLogOff() {
        return datLogOff != null;
    }

    public boolean isSetDatLastActivity() {
        return datLastActivity != null;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDatLogon() {
        return datLogon;
    }

    public void setDatLogon(Date datLogon) {
        this.datLogon = datLogon;
    }

    public Date getDatLogOff() {
        return datLogOff;
    }

    public void setDatLogOff(Date datLogOff) {
        this.datLogOff = datLogOff;
    }

    public Date getDatLastActivity() {
        return datLastActivity;
    }

    public void setDatLastActivity(Date datLastActivity) {
        this.datLastActivity = datLastActivity;
    }
}
