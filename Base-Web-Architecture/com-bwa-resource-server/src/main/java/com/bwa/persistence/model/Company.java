package com.bwa.persistence.model;

import com.bwa.persistence.model.common.GeneratedIdEntry;
import com.bwa.persistence.model.common.UpdatableDbEntry;

import javax.persistence.*;

/**
 * Created by Hello Hassnain on 07/05/2017.
 */
@Entity
@Table(name="ITOB_COMPANY")
@AttributeOverride(name ="id" , column = @Column(name = "ID_COMPANY_CODE"))
public class Company extends GeneratedIdEntry {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "STR_COMPANY_NAME", length = 30)
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isSetCompanyName(){
        return companyName != null;
    }
}
