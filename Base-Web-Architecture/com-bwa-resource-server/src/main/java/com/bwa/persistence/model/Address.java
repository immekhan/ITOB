package com.bwa.persistence.model;

import com.bwa.persistence.model.common.SpareFieldsDbEntry;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ITOB_ADDRESSES")
@AttributeOverride(name="id", column=@Column(name="ID_ADDRESS"))
public class Address
        extends SpareFieldsDbEntry
{

    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ID_CUSTOMER", nullable=true)
    private Customer customer;
    @Basic(optional=false)
    @Column(name="ID_ADDRESS_TYPE", nullable=false)
    private Integer addressType;
    @Basic(optional=false)
    @Column(name="ID_ADDRESS_STATUS", nullable=false)
    private Integer addressStatus;
    @Basic(optional=true)
    @Column(name="STR_FIRST_NAME", nullable=true, length=80)
    private String firstName;
    @Basic(optional=true)
    @Column(name="STR_MIDDLE_NAME", nullable=true, length=80)
    private String middleName;
    @Basic(optional=true)
    @Column(name="STR_LAST_NAME", nullable=true, length=80)
    private String lastName;
    @Basic(optional=true)
    @Column(name="ID_GENDER", nullable=true)
    private Integer gender;
    @Basic(optional=true)
    @Column(name="STR_TITLE", nullable=true, length=80)
    private String title;
    @Basic(optional=true)
    @Column(name="STR_COMPANY1", nullable=true, length=80)
    private String company1;
    @Basic(optional=true)
    @Column(name="STR_COMPANY2", nullable=true, length=80)
    private String company2;
    @Basic(optional=true)
    @Column(name="STR_COMPANY_SHORTNAME", nullable=true, length=80)
    private String companyShortname;
    @Basic(optional=true)
    @Column(name="STR_POSITION", nullable=true, length=80)
    private String position;
    @Basic(optional=true)
    @Column(name="STR_STREET1", nullable=true, length=80)
    private String street1;
    @Basic(optional=true)
    @Column(name="STR_STREET2", nullable=true, length=80)
    private String street2;
    @Basic(optional=true)
    @Column(name="STR_HOUSE_NUMBER", nullable=true, length=80)
    private String houseNumber;
    @Basic(optional=true)
    @Column(name="STR_ZIP", nullable=true, length=80)
    private String zip;
    @Basic(optional=true)
    @Column(name="STR_CITY", nullable=true, length=80)
    private String city;
    @Basic(optional=true)
    @Column(name="STR_STATE", nullable=true, length=80)
    private String state;
    @Basic(optional=true)
    @Column(name="ID_COUNTRY", nullable=true, length=80)
    private String countryId;
    @Basic(optional=true)
    @Column(name="STR_PHONE1", nullable=true, length=80)
    private String phone1;
    @Basic(optional=true)
    @Column(name="STR_PHONE2", nullable=true, length=80)
    private String phone2;
    @Basic(optional=true)
    @Column(name="STR_FAX", nullable=true, length=80)
    private String fax;
    @Basic(optional=true)
    @Column(name="STR_EMAIL", nullable=true, length=80)
    private String email;
    @Basic(optional=true)
    @Column(name="STR_URL", nullable=true, length=80)
    private String url;
    @Basic(optional=true)
    @Column(name="DAT_ADDRESS_SINCE", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date addressSince;
    @Basic(optional=true)
    @Column(name="STR_NAME_ADDRESS", nullable=true, length=80)
    private String alias;
    @Basic(optional=true)
    @Column(name="GEO_LATITUDE", nullable=true, length=18, scale=12)
    private BigDecimal latitude;
    @Basic(optional=true)
    @Column(name="GEO_LONGITUDE", nullable=true, length=18, scale=12)
    private BigDecimal longitude;
    @Basic(optional=true)
    @Column(name="INT_ACCURACY", nullable=true)
    private Integer accuracy;
    @Basic(optional=true)
    @Column(name="ID_LOCATION_TYPE", nullable=true)
    private Integer locationType;

    public Address(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public Address(){
        this.setCreationDate(new Date());
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

    public String getCountryId()
    {
        return this.countryId;
    }

    public boolean isSetCountryId()
    {
        return this.countryId != null;
    }

    public void setCountry(String countryId)
    {
        this.countryId = countryId;
    }

    public Integer getGender()
    {
        return this.gender;
    }

    public boolean isSetGender()
    {
        return this.gender != null;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Date getAddressSince()
    {
        return this.addressSince == null ? null : new Date(this.addressSince.getTime());
    }

    public boolean isSetAddressSince()
    {
        return this.addressSince != null;
    }

    public void setAddressSince(Date addressSince)
    {
        this.addressSince = (addressSince != null ? new Date(addressSince.getTime()) : null);
    }

    public String getCity()
    {
        return this.city;
    }

    public boolean isSetCity()
    {
        return this.city != null;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCompany1()
    {
        return this.company1;
    }

    public boolean isSetCompany1()
    {
        return this.company1 != null;
    }

    public void setCompany1(String company1)
    {
        this.company1 = company1;
    }

    public String getCompany2()
    {
        return this.company2;
    }

    public boolean isSetCompany2()
    {
        return this.company2 != null;
    }

    public void setCompany2(String company2)
    {
        this.company2 = company2;
    }

    public String getCompanyShortname()
    {
        return this.companyShortname;
    }

    public boolean isSetCompanyShortname()
    {
        return this.companyShortname != null;
    }

    public void setCompanyShortname(String companyShortname)
    {
        this.companyShortname = companyShortname;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean isSetEmail()
    {
        return this.email != null;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFax()
    {
        return this.fax;
    }

    public boolean isSetFax()
    {
        return this.fax != null;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public boolean isSetFirstName()
    {
        return this.firstName != null;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getHouseNumber()
    {
        return this.houseNumber;
    }

    public boolean isSetHouseNumber()
    {
        return this.houseNumber != null;
    }

    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public boolean isSetLastName()
    {
        return this.lastName != null;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getMiddleName()
    {
        return this.middleName;
    }

    public boolean isSetMiddleName()
    {
        return this.middleName != null;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getPhone1()
    {
        return this.phone1;
    }

    public boolean isSetPhone1()
    {
        return this.phone1 != null;
    }

    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }

    public String getPhone2()
    {
        return this.phone2;
    }

    public boolean isSetPhone2()
    {
        return this.phone2 != null;
    }

    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }

    public String getPosition()
    {
        return this.position;
    }

    public boolean isSetPosition()
    {
        return this.position != null;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getState()
    {
        return this.state;
    }

    public boolean isSetState()
    {
        return this.state != null;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getStreet1()
    {
        return this.street1;
    }

    public boolean isSetStreet1()
    {
        return this.street1 != null;
    }

    public void setStreet1(String street1)
    {
        this.street1 = street1;
    }

    public String getStreet2()
    {
        return this.street2;
    }

    public boolean isSetStreet2()
    {
        return this.street2 != null;
    }

    public void setStreet2(String street2)
    {
        this.street2 = street2;
    }

    public String getTitle()
    {
        return this.title;
    }

    public boolean isSetTitle()
    {
        return this.title != null;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrl()
    {
        return this.url;
    }

    public boolean isSetUrl()
    {
        return this.url != null;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getZip()
    {
        return this.zip;
    }

    public boolean isSetZip()
    {
        return this.zip != null;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public boolean isSetAlias()
    {
        return this.alias != null;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public int getAddressType()
    {
        return this.addressType.intValue();
    }

    public boolean isSetAddressType()
    {
        return this.addressType != null;
    }

    public void setAddressType(int addressType)
    {
        this.addressType = Integer.valueOf(addressType);
    }

    public int getAddressStatus()
    {
        return this.addressStatus.intValue();
    }

    public boolean isSetAddressStatus()
    {
        return this.addressStatus != null;
    }

    public void setAddressStatus(int addressStatus)
    {
        this.addressStatus = Integer.valueOf(addressStatus);
    }

    public BigDecimal getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    public boolean isSetLatitude()
    {
        return this.latitude != null;
    }

    public BigDecimal getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    public boolean isSetLongitude()
    {
        return this.longitude != null;
    }

    public Integer getAccuracy()
    {
        return this.accuracy;
    }

    public void setAccuracy(Integer accuracy)
    {
        this.accuracy = accuracy;
    }

    public boolean isSetAccuracy()
    {
        return this.accuracy != null;
    }

    public Integer getLocationType()
    {
        return this.locationType;
    }

    public void setLocationType(Integer locationType)
    {
        this.locationType = locationType;
    }

    public boolean isSetLocationType()
    {
        return this.locationType != null;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public void setAddressStatus(Integer addressStatus) {
        this.addressStatus = addressStatus;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}

