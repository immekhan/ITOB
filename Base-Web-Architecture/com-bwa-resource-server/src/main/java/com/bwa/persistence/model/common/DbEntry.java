package com.bwa.persistence.model.common;

import com.bwa.persistence.model.interfaces.CreatableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class DbEntry
        implements CreatableEntity, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final int LENGTH_CHAR_ONE = 1;
    public static final int LENGTH_CHAR_TWO = 2;
    public static final int LENGTH_CHAR_THREE = 3;
    public static final int LENGTH_STRING_SMALL = 6;
    public static final int LENGTH_STRING_MEDIUM = 80;
    public static final int LENGTH_STRING_LARGE = 200;
    public static final int LENGTH_STRING_HUGE = 2048;
    public static final int PRECISION_SHORT = 5;
    public static final int PRECISION_LONG = 18;
    public static final String PBX_TRUE = "Y";
    public static final String PBX_FALSE = "N";

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_CREATION", nullable=true, updatable=false)
    private Date creationDate;

    @Basic(optional=true)
    @Column(name="ID_CUSTOMER_CREATION", nullable=true, updatable=false)
    private Long creator;

    public String toString()
    {
        StringBuilder builder = new StringBuilder(128);
        builder.append(super.getClass().getSimpleName());
        Date creation = getCreationDate();
        if (creation != null) {
            builder.append("; created on ");
            builder.append(creation);
        } else {
            builder.append("; not yet persisted");
        }
        return builder.toString();
    }

    public Date getCreationDate()
    {
        if (this.creationDate == null) {
            return null;
        }
        return new Date(this.creationDate.getTime());
    }

    public boolean isSetCreationDate()
    {
        return (this.creationDate != null);
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = new Date(creationDate.getTime());
    }

    public Long getCreator()
    {
        return this.creator;
    }

    public boolean isSetCreator()
    {
        return (this.creator != null);
    }

    public void setCreator(Long creator)
    {
        this.creator = creator;
    }
}
