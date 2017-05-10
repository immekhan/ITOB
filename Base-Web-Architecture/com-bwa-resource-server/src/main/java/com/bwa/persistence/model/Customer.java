package com.bwa.persistence.model;

import com.bwa.persistence.model.common.SpareFieldsDbEntry;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="ITOB_CUSTOMERS")
@AttributeOverride(name="id", column=@Column(name="ID_CUSTOMER"))
public class Customer
        extends SpareFieldsDbEntry
{
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_ORGUNIT", nullable=false)
    private OrgUnit orgUnit;

    @Basic(optional=false)
    @Column(name="BOL_IS_ACTIVE", nullable=false)
    private Character dbActive = Character.valueOf('Y');

    @Basic(optional=false)
    @Column(name="BOL_IS_TEST", nullable=false)
    private Character dbTest = Character.valueOf('N');

    @Basic(optional=false)
    @Column(name="ID_BLACKLISTREASON", nullable=false)
    private Integer blacklistReason = Integer.valueOf(0);

    @Basic(optional=true)
    @Column(name="STR_DISPLAY_NAME", nullable=true, length=80)
    private String displayName;

    @Basic(optional=true)
    @Column(name="ID_TIME_ZONE", nullable=true, length=80)
    private String dbTimeZone;

    @Basic(optional=true)
    @Column(name="ID_LANGUAGE", nullable=true, length=2)
    private String languageId;

    @Basic(optional=true)
    @Column(name="ID_COUNTRY", nullable=true, length=2)
    private String countryId;

    @Basic(optional=true)
    @Column(name="STR_SECURITY_QUESTION", nullable=true, length=80)
    private String securityQuestion;

    @Basic(optional=true)
    @Column(name="STR_SECURITY_ANSWER", nullable=true, length=80)
    private String securityAnswer;

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ID_PARENT", nullable=true)
    private Customer parent;

    @Basic(optional=true)
    @Column(name="DAT_DATE_OF_BIRTH", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;


    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="ID_CUSTOMER_TYPE", nullable=false)
    private CustomerType customerType;

    @Basic(optional=false)
    @Column(name="ID_CANCELLATION_REASON", nullable=false)
    private Integer cancellationReason = Integer.valueOf(0);

    @Basic(optional = true)
    @Column(name = "MOBILE_NO" , nullable = true)
    private String mobileNo;

    @Basic(optional = true)
    @Column(name = "USER_ID" , nullable = true)
    private String userId;

    @Basic(optional = true)
    @Column(name = "EMAIL" , nullable = true)
    private String email;

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

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public Character getDbActive() {
        return dbActive;
    }

    public void setDbActive(Character dbActive) {
        this.dbActive = dbActive;
    }

    public boolean getDbTest() {
        return dbTest.equals('Y');
    }

    public void setDbTest(boolean isTest) {
        this.dbTest = isTest?'Y':'N';
    }

    public Integer getBlacklistReason() {
        return blacklistReason;
    }

    public void setBlacklistReason(Integer blacklistReason) {
        this.blacklistReason = blacklistReason;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDbTimeZone() {
        return dbTimeZone;
    }

    public void setDbTimeZone(String dbTimeZone) {
        this.dbTimeZone = dbTimeZone;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Customer getParent() {
        return parent;
    }

    public void setParent(Customer parent) {
        this.parent = parent;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Integer getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(Integer cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
