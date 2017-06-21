package com.bwa.persistence.model;


import com.bwa.persistence.model.common.GeneratedIdEntry;
import com.bwa.persistence.model.common.UpdatableDbEntry;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ITOB_CUSTOMER_TYPES")
@AttributeOverride(name="id", column=@Column(name="ID_CUSTOMER_TYPE"))
public class CustomerType
        extends GeneratedIdEntry
{
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "STR_CUSTOMER_TYPE" ,nullable = false , length = 80)
    private String strCustomerType;

    @Basic(optional=true)
    @Column(name="ID_UMGR_ROLE", nullable=true, length=80)
    private String strRole;

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ID_SESSION_POLICY", nullable=true)
    private SessionPolicy sessionPolicy;

    @Basic(optional=false)
    @Column(name="BOL_IS_INTERNAL", nullable=false)
    private Character dbInternal = Character.valueOf('N');

    public CustomerType(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public CustomerType(){
        this.setCreationDate(new Date());
    }

    public boolean isInternal()
    {
        return this.dbInternal.equals(Character.valueOf('Y'));
    }

    public boolean isSetInternal()
    {
        return this.dbInternal != null;
    }

    public SessionPolicy getSessionPolicy()
    {
        return this.sessionPolicy;
    }

    public boolean isSetSessionPolicy()
    {
        return this.sessionPolicy != null;
    }

    public void setSessionPolicy(SessionPolicy sessionPolicy)
    {
        this.sessionPolicy = sessionPolicy;
    }

    public void setInternal(boolean internal)
    {
        this.dbInternal = Character.valueOf(internal ? 'Y' : 'N');
    }

    public String getStrCustomerType() {
        return strCustomerType;
    }

    public void setStrCustomerType(String strCustomerType) {
        this.strCustomerType = strCustomerType;
    }

    public String getStrRole() {
        return strRole;
    }

    public void setStrRole(String strRole) {
        this.strRole = strRole;
    }

}
