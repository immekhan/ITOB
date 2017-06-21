package com.bwa.persistence.model;

import com.bwa.persistence.model.common.UpdatableDbEntry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ITOB_SESSIONS")
public class Session extends UpdatableDbEntry
        implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "STR_SESSION_ID" , nullable = false ,length = 80)
    private String id;

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

    public Session(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public Session(){
        this.setCreationDate(new Date());
    }

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

    public boolean isSetId() {
        return id != null;
    }

    public String getId() {
        return id;
    }

    public Session setId(String id) {
        this.id = id;
        return this;
    }
}
