package com.bwa.persistence.model.common;

import com.bwa.persistence.model.interfaces.UpdatableEntity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class UpdatableDbEntry extends DbEntry
        implements UpdatableEntity
{
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    @Column(name="DAT_LAST_UPDATE", nullable=true)
    private Date lastUpdate;

    @Basic(optional=true)
    @Column(name="ID_CUSTOMER_LAST_UPDATE", nullable=true)
    private Long lastUpdater;

    public Date getLastUpdate()
    {
        if (this.lastUpdate == null) {
            return null;
        }
        return new Date(this.lastUpdate.getTime());
    }

    public boolean isSetLastUpdate()
    {
        return (this.lastUpdate != null);
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = new Date(lastUpdate.getTime());
    }

    public Long getLastUpdater()
    {
        return this.lastUpdater;
    }

    public boolean isSetLastUpdater()
    {
        return (this.lastUpdater != null);
    }

    public void setLastUpdater(Long lastUpdater)
    {
        this.lastUpdater = lastUpdater;
    }
}