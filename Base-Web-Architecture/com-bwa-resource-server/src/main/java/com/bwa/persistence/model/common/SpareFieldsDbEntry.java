package com.bwa.persistence.model.common;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class SpareFieldsDbEntry extends GeneratedIdEntry
{
    private static final long serialVersionUID = 1L;

    @Basic(optional=true)
    @Column(name="STR_SPARE_1", nullable=true, length=80)
    private String strSpare1;

    @Basic(optional=true)
    @Column(name="STR_SPARE_2", nullable=true, length=80)
    private String strSpare2;

    @Basic(optional=true)
    @Column(name="STR_SPARE_3", nullable=true, length=80)
    private String strSpare3;

    @Basic(optional=true)
    @Column(name="STR_SPARE_4", nullable=true, length=80)
    private String strSpare4;

    @Basic(optional=true)
    @Column(name="STR_SPARE_5", nullable=true, length=80)
    private String strSpare5;

    @Basic(optional=true)
    @Column(name="STR_SPARE_6", nullable=true, length=80)
    private String strSpare6;

    @Basic(optional=true)
    @Column(name="STR_SPARE_7", nullable=true, length=80)
    private String strSpare7;

    @Basic(optional=true)
    @Column(name="STR_SPARE_8", nullable=true, length=80)
    private String strSpare8;

    @Basic(optional=true)
    @Column(name="STR_SPARE_9", nullable=true, length=80)
    private String strSpare9;

    @Basic(optional=true)
    @Column(name="STR_SPARE_10", nullable=true, length=80)
    private String strSpare10;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_SPARE_1", nullable=true)
    private Date datSpare1;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_SPARE_2", nullable=true)
    private Date datSpare2;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_SPARE_3", nullable=true)
    private Date datSpare3;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_SPARE_4", nullable=true)
    private Date datSpare4;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_SPARE_5", nullable=true)
    private Date datSpare5;

    @Basic(optional=true)
    @Column(name="INT_SPARE_1", nullable=true, precision=18)
    private Long intSpare1;

    @Basic(optional=true)
    @Column(name="INT_SPARE_2", nullable=true, precision=18)
    private Long intSpare2;

    @Basic(optional=true)
    @Column(name="INT_SPARE_3", nullable=true, precision=18)
    private Long intSpare3;

    @Basic(optional=true)
    @Column(name="INT_SPARE_4", nullable=true, precision=18)
    private Long intSpare4;

    @Basic(optional=true)
    @Column(name="INT_SPARE_5", nullable=true, precision=18)
    private Long intSpare5;

    @Basic(optional=true)
    @Column(name="BOL_SPARE_1", nullable=true)
    private Character dbBolSpare1;

    @Basic(optional=true)
    @Column(name="BOL_SPARE_2", nullable=true)
    private Character dbBolSpare2;

    @Basic(optional=true)
    @Column(name="BOL_SPARE_3", nullable=true)
    private Character dbBolSpare3;

    @Basic(optional=true)
    @Column(name="BOL_SPARE_4", nullable=true)
    private Character dbBolSpare4;

    @Basic(optional=true)
    @Column(name="BOL_SPARE_5", nullable=true)
    private Character dbBolSpare5;

    @Lob
    @Basic(optional=true, fetch=FetchType.LAZY)
    @Column(name="CLOB_SPARE_1", nullable=true)
    private String clobSpare1;

    public String getStrSpare1()
    {
        return this.strSpare1;
    }

    public void setStrSpare1(String strSpare1) {
        this.strSpare1 = strSpare1;
    }

    public boolean isSetStrSpare1() {
        return (this.strSpare1 != null);
    }

    public String getStrSpare2() {
        return this.strSpare2;
    }

    public void setStrSpare2(String strSpare2) {
        this.strSpare2 = strSpare2;
    }

    public boolean isSetStrSpare2() {
        return (this.strSpare2 != null);
    }

    public String getStrSpare3() {
        return this.strSpare3;
    }

    public void setStrSpare3(String strSpare3) {
        this.strSpare3 = strSpare3;
    }

    public boolean isSetStrSpare3() {
        return (this.strSpare3 != null);
    }

    public String getStrSpare4() {
        return this.strSpare4;
    }

    public void setStrSpare4(String strSpare4) {
        this.strSpare4 = strSpare4;
    }

    public boolean isSetStrSpare4() {
        return (this.strSpare4 != null);
    }

    public String getStrSpare5() {
        return this.strSpare5;
    }

    public void setStrSpare5(String strSpare5) {
        this.strSpare5 = strSpare5;
    }

    public boolean isSetStrSpare5() {
        return (this.strSpare5 != null);
    }

    public String getStrSpare6() {
        return this.strSpare6;
    }

    public void setStrSpare6(String strSpare6) {
        this.strSpare6 = strSpare6;
    }

    public boolean isSetStrSpare6() {
        return (this.strSpare6 != null);
    }

    public String getStrSpare7() {
        return this.strSpare7;
    }

    public void setStrSpare7(String strSpare7) {
        this.strSpare7 = strSpare7;
    }

    public boolean isSetStrSpare7() {
        return (this.strSpare7 != null);
    }

    public String getStrSpare8() {
        return this.strSpare8;
    }

    public void setStrSpare8(String strSpare8) {
        this.strSpare8 = strSpare8;
    }

    public boolean isSetStrSpare8() {
        return (this.strSpare8 != null);
    }

    public String getStrSpare9() {
        return this.strSpare9;
    }

    public void setStrSpare9(String strSpare9) {
        this.strSpare9 = strSpare9;
    }

    public boolean isSetStrSpare9() {
        return (this.strSpare9 != null);
    }

    public String getStrSpare10() {
        return this.strSpare10;
    }

    public void setStrSpare10(String strSpare10) {
        this.strSpare10 = strSpare10;
    }

    public boolean isSetStrSpare10() {
        return (this.strSpare10 != null);
    }

    public Date getDatSpare1() {
        if (this.datSpare1 == null) {
            return null;
        }
        return new Date(this.datSpare1.getTime());
    }

    public void setDatSpare1(Date datSpare1) {
        this.datSpare1 = new Date(datSpare1.getTime());
    }

    public boolean isSetDatSpare1()
    {
        return (this.datSpare1 != null);
    }

    public Date getDatSpare2() {
        if (this.datSpare2 == null) {
            return null;
        }
        return new Date(this.datSpare2.getTime());
    }

    public void setDatSpare2(Date datSpare2) {
        this.datSpare2 = new Date(datSpare2.getTime());
    }

    public boolean isSetDatSpare2()
    {
        return (this.datSpare2 != null);
    }

    public Date getDatSpare3() {
        if (this.datSpare3 == null) {
            return null;
        }
        return new Date(this.datSpare3.getTime());
    }

    public void setDatSpare3(Date datSpare3) {
        this.datSpare3 = new Date(datSpare3.getTime());
    }

    public boolean isSetDatSpare3()
    {
        return (this.datSpare3 != null);
    }

    public Date getDatSpare4() {
        if (this.datSpare4 == null) {
            return null;
        }
        return new Date(this.datSpare4.getTime());
    }

    public void setDatSpare4(Date datSpare4) {
        this.datSpare4 = new Date(datSpare4.getTime());
    }

    public boolean isSetDatSpare4()
    {
        return (this.datSpare4 != null);
    }

    public Date getDatSpare5() {
        if (this.datSpare5 == null) {
            return null;
        }
        return new Date(this.datSpare5.getTime());
    }

    public void setDatSpare5(Date datSpare5) {
        this.datSpare5 = new Date(datSpare5.getTime());
    }

    public boolean isSetDatSpare5()
    {
        return (this.datSpare5 != null);
    }

    public Long getIntSpare1() {
        return this.intSpare1;
    }

    public void setIntSpare1(Long intSpare1) {
        this.intSpare1 = intSpare1;
    }

    public boolean isSetIntSpare1() {
        return (this.intSpare1 != null);
    }

    public Long getIntSpare2() {
        return this.intSpare2;
    }

    public void setIntSpare2(Long intSpare2) {
        this.intSpare2 = intSpare2;
    }

    public boolean isSetIntSpare2() {
        return (this.intSpare2 != null);
    }

    public Long getIntSpare3() {
        return this.intSpare3;
    }

    public void setIntSpare3(Long intSpare3) {
        this.intSpare3 = intSpare3;
    }

    public boolean isSetIntSpare3() {
        return (this.intSpare3 != null);
    }

    public Long getIntSpare4() {
        return this.intSpare4;
    }

    public void setIntSpare4(Long intSpare4) {
        this.intSpare4 = intSpare4;
    }

    public boolean isSetIntSpare4() {
        return (this.intSpare4 != null);
    }

    public Long getIntSpare5() {
        return this.intSpare5;
    }

    public void setIntSpare5(Long intSpare5) {
        this.intSpare5 = intSpare5;
    }

    public boolean isSetIntSpare5() {
        return (this.intSpare5 != null);
    }

    public Boolean getBolSpare1() {
        return ((this.dbBolSpare1 == null) ? null : Boolean.valueOf(this.dbBolSpare1.equals(Character.valueOf('Y'))));
    }

    public void setBolSpare1(Boolean bolSpare1)
    {
        this.dbBolSpare1 = ((bolSpare1 == null) ? null : Character.valueOf((bolSpare1.booleanValue()) ? 'Y' : 'N'));
    }

    public boolean isSetBolSpare1()
    {
        return (this.dbBolSpare1 != null);
    }

    public Boolean getBolSpare2() {
        return ((this.dbBolSpare2 == null) ? null : Boolean.valueOf(this.dbBolSpare2.equals(Character.valueOf('Y'))));
    }

    public void setBolSpare2(Boolean bolSpare2)
    {
        this.dbBolSpare2 = ((bolSpare2 == null) ? null : Character.valueOf((bolSpare2.booleanValue()) ? 'Y' : 'N'));
    }

    public boolean isSetBolSpare2()
    {
        return (this.dbBolSpare2 != null);
    }

    public Boolean getBolSpare3() {
        return ((this.dbBolSpare3 == null) ? null : Boolean.valueOf(this.dbBolSpare3.equals(Character.valueOf('Y'))));
    }

    public void setBolSpare3(Boolean bolSpare3)
    {
        this.dbBolSpare3 = ((bolSpare3 == null) ? null : Character.valueOf((bolSpare3.booleanValue()) ? 'Y' : 'N'));
    }

    public boolean isSetBolSpare3()
    {
        return (this.dbBolSpare3 != null);
    }

    public Boolean getBolSpare4() {
        return ((this.dbBolSpare4 == null) ? null : Boolean.valueOf(this.dbBolSpare4.equals(Character.valueOf('Y'))));
    }

    public void setBolSpare4(Boolean bolSpare4)
    {
        this.dbBolSpare4 = ((bolSpare4 == null) ? null : Character.valueOf((bolSpare4.booleanValue()) ? 'Y' : 'N'));
    }

    public boolean isSetBolSpare4()
    {
        return (this.dbBolSpare4 != null);
    }

    public Boolean getBolSpare5() {
        return ((this.dbBolSpare5 == null) ? null : Boolean.valueOf(this.dbBolSpare5.equals(Character.valueOf('Y'))));
    }

    public void setBolSpare5(Boolean bolSpare5)
    {
        this.dbBolSpare5 = ((bolSpare5 == null) ? null : Character.valueOf((bolSpare5.booleanValue()) ? 'Y' : 'N'));
    }

    public boolean isSetBolSpare5()
    {
        return (this.dbBolSpare5 != null);
    }

    public String getClobSpare1() {
        return this.clobSpare1;
    }

    public void setClobSpare1(String clobSpare1) {
        this.clobSpare1 = clobSpare1;
    }

    public boolean isSetClobSpare1() {
        return (this.clobSpare1 != null);
    }
}