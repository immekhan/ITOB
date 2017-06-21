package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Entity
@Table(name="ITOB_ORGUNITS")
@AttributeOverride(name="id", column=@Column(name="ID_ORGUNIT", length=6))
public class OrgUnit
        extends IdEntry<String>
{
    private static final long serialVersionUID = 1L;
    @Basic(optional=false)
    @Column(name="STR_ORGUNIT", nullable=false, length=80)
    private String name;
    @Basic(optional=true)
    @Column(name="ID_TIME_ZONE", nullable=true, length=80)
    private String dbTimeZone;
    @Basic(optional=false)
    @Column(name="ID_LANGUAGE", nullable=false, length=2)
    private String dbLanguage;
    @Basic(optional=true)
    @Column(name="ID_COUNTRY", nullable=true, length=2)
    private String dbCountry;
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ID_LEGAL_ADDRESS", nullable=true)
    private Address legalAddress;
    @Basic(optional=true)
    @Column(name="INT_AUTO_CANCEL_AFTER_MINUTES", nullable=true)
    private Integer autoCancelAfterMinutes;

    public OrgUnit(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public OrgUnit(){
        this.setCreationDate(new Date());
    }

    public Locale getLocale()
    {
        return new Locale(this.dbLanguage, this.dbCountry == null ? "" : this.dbCountry, (String)getId());
    }

    public boolean isSetLocale()
    {
        return (this.dbLanguage != null) || (this.dbCountry != null);
    }

    public void setLocale(Locale locale)
    {
        if (locale == null)
        {
            this.dbLanguage = null;
            this.dbCountry = null;
            return;
        }
        this.dbLanguage = ((locale.getLanguage() != null) && (locale.getLanguage().length() > 0) ? locale.getLanguage() : null);

        this.dbCountry = ((locale.getCountry() != null) && (locale.getCountry().length() > 0) ? locale.getCountry() : null);
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isSetName()
    {
        return this.name != null;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public TimeZone getTimeZone()
    {
        return this.dbTimeZone != null ? TimeZone.getTimeZone(this.dbTimeZone) : null;
    }

    public boolean isSetTimeZone()
    {
        return this.dbTimeZone != null;
    }

    public void setTimeZone(TimeZone timeZone)
    {
        this.dbTimeZone = (timeZone != null ? timeZone.getID() : null);
    }

    public Address getLegalAddress()
    {
        return this.legalAddress;
    }

    public boolean isSetLegalAddress()
    {
        return this.legalAddress != null;
    }

    public void setLegalAddress(Address legalAddress)
    {
        this.legalAddress = legalAddress;
    }

    public Integer getAutoCancelAfterMinutes()
    {
        return this.autoCancelAfterMinutes;
    }

    public boolean isSetAutoCancelAfterMinutes()
    {
        return this.autoCancelAfterMinutes != null;
    }

    public void setAutoCancelAfterMinutes(Integer autoCancelAfterMinutes)
    {
        this.autoCancelAfterMinutes = autoCancelAfterMinutes;
    }

}

