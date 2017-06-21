package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ITOB_PRIVILEGES")
@AttributeOverride(name="id", column=@Column(name="ID_PRIVILEGE", length=80))
public class Privilege
        extends IdEntry<String>
{
    private static final long serialVersionUID = 1L;
    @Basic(optional=true)
    @Column(name="STR_PRIVILEGE", nullable=true, length=80)
    private String description;

    public Privilege(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public Privilege(){
        this.setCreationDate(new Date());
    }

    @Deprecated
    public Privilege(String id)
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
}
