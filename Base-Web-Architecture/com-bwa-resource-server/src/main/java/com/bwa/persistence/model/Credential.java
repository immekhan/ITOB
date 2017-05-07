package com.bwa.persistence.model;

/**
 * Created by Hello Hassnain on 07/05/2017.
 */

import com.bwa.persistence.model.common.GeneratedIdEntry;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITOB_CUSTOMERS_CREDENTIALS")
@AttributeOverride(name="id", column=@Column(name="ID_CUSTOMER_CREDENTIAL"))
public class Credential
        extends GeneratedIdEntry
{
    private static final long serialVersionUID = 1L;

    @Basic(optional=false)
    @Column(name="ID_CREDENTIAL_TYPE", nullable=false)
    private Integer credentialType;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_CUSTOMER", nullable=false)
    private Customer customer;

    @Basic(optional=false)
    @Column(name="STR_CREDENTIAL", nullable=false, length=200)
    private String credential;

    @Basic(optional=false)
    @Column(name="BOL_IS_ACTIVE", nullable=false)
    private Character dbActive = Character.valueOf('Y');

    @Basic(optional=false)
    @Column(name="ID_CREDENTIAL_STATUS", nullable=false)
    private Integer credentialStatus = Integer.valueOf(0);

    @Basic(optional=false)
    @Column(name="INT_WRONG_CREDENTIALS", nullable=false)
    private Integer wrongCredentialCount = Integer.valueOf(0);

    @Basic(optional=true)
    @Column(name="DAT_BLOCKED_UNTIL", nullable=true)
    private Date blockedUntil;

    @Basic(optional=true)
    @Column(name="DAT_LAST_USED", nullable=true)
    private Date lastUsed;

    public boolean isActive()
    {
        return this.dbActive.equals(Character.valueOf('Y'));
    }

    public boolean isSetActive()
    {
        return this.dbActive != null;
    }

    public void setActive(boolean active)
    {
        this.dbActive = Character.valueOf(active ? 'Y' : 'N');
    }

    public Customer getCustomer()
    {
        return this.customer;
    }

    public boolean isSetCustomer()
    {
        return this.customer != null;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getCredential()
    {
        return this.credential;
    }

    public boolean isSetCredential()
    {
        return this.credential != null;
    }

    public void setCredential(String credential)
    {
        this.credential = credential;
    }

    public int getCredentialType()
    {
        return this.credentialType.intValue();
    }

    public boolean isSetCredentialType()
    {
        return this.credentialType != null;
    }

    public void setCredentialType(int credentialType)
    {
        this.credentialType = Integer.valueOf(credentialType);
    }

    public int getCredentialStatus()
    {
        return this.credentialStatus.intValue();
    }

    public boolean isSetCredentialStatus()
    {
        return this.credentialStatus != null;
    }

    public void setCredentialStatus(int credentialStatus)
    {
        this.credentialStatus = Integer.valueOf(credentialStatus);
    }

    public int getWrongCredentialCount()
    {
        return this.wrongCredentialCount.intValue();
    }

    public boolean isSetWrongCredentialCount()
    {
        return this.wrongCredentialCount != null;
    }

    public void setWrongCredentialCount(int wrongCredentialCount)
    {
        this.wrongCredentialCount = Integer.valueOf(wrongCredentialCount);
    }

    public Date getBlockedUntil()
    {
        return this.blockedUntil;
    }

    public boolean isSetBlockedUntil()
    {
        return this.blockedUntil != null;
    }

    public void setBlockedUntil(Date blockedUntil)
    {
        this.blockedUntil = blockedUntil;
    }

    public Date getLastUsed()
    {
        return this.lastUsed;
    }

    public void setLastUsed(Date lastUsed)
    {
        this.lastUsed = lastUsed;
    }

    public boolean isSetLastUsed()
    {
        return this.lastUsed != null;
    }
}

