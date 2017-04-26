package com.bwa.persistence.model.common;

import com.bwa.persistence.model.interfaces.IdEntity;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class CompositeIdEntry<ID extends Serializable> extends UpdatableDbEntry
        implements IdEntity<ID>, Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ID id;

    @Deprecated
    protected CompositeIdEntry()
    {
    }

    protected CompositeIdEntry(ID id)
    {
        this.id = id;
    }

    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        buf.append(super.getClass().getSimpleName());
        Serializable dummy = this.id;
        if (dummy == null) {
            buf.append(" [not yet persisted]");
        } else {
            buf.append(" #");
            buf.append(dummy);
        }
        return buf.toString();
    }

    public boolean equals(Object o)
    {
        if (o == null) {
            return false;
        }
        if (this.id == null) {
            return false;
        }
        if (!(super.getClass().isAssignableFrom(o.getClass()))) {
            return false;
        }

        CompositeIdEntry other = (CompositeIdEntry)o;

        return this.id.equals(other.getId());
    }

    public int hashCode()
    {
        return ((this.id == null) ? 0 : this.id.hashCode());
    }

    public ID getId()
    {
        return this.id;
    }

    public boolean isSetId() {
        return (this.id != null);
    }
}