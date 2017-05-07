package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;

@Entity
@Table(name="MOB_ROLES")
@AttributeOverride(name="id", column=@Column(name="ID_ROLE", length=80))
public class Role
        extends IdEntry<String>
{
    private static final long serialVersionUID = 1L;
    @Basic(optional=true)
    @Column(name="STR_ROLE", nullable=true, length=80)
    private String description;

    @Deprecated
    protected Role() {}

    @Deprecated
    public Role(String id)
    {
        super(id);
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isSetDescription()
    {
        return this.description != null;
    }
}
