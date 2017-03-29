package com.bwa.persistence.model;

import javax.persistence.*;

/**
 * Created by Hassnain on 12/02/2017.
 */

@Entity
@Table(name = "HR_USER")
public class UserMBean {

    @Id
    @Column(name ="ID_USER",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idSequence")
    @SequenceGenerator(name = "idSequence", sequenceName = "HR.HR_USER_SEQ", allocationSize = 1)
    private long id;

    @Basic(optional = false)
    @Column(name = "STR_NAME",nullable = false,length = 20)
    private String strName;

    @Basic(optional = false)
    @Column(name = "STR_PHONE",nullable = false,length = 20)
    private String phoneNo;

    @Basic(optional = false)
    @Column(name = "STR_ID_ADDRESS",nullable = false,length = 5)
    private String addressId;

    public long getId() {
        return id;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
