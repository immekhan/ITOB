package com.bwa.persistence.model;

import com.bwa.persistence.model.common.GeneratedIdEntry;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ITOB_ATTACHMENTS")
@AttributeOverride(name="id", column=@Column(name="ID_ATTACHMENT"))
public class Attachment
        extends GeneratedIdEntry
{
    private static final long serialVersionUID = 1L;

    @Basic(optional=false)
    @Column(name="ID_ATTACHMENT_TYPE", nullable=false)
    private Integer type;

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="ID_CUSTOMER", nullable=true)
    private Customer customer;

    @Basic(optional=false)
    @Column(name="STR_NAME", nullable=false, length=80)
    private String name;

    @Basic(optional=true)
    @Column(name="STR_CONTENT_TYPE", nullable=true, length=80)
    private String contentType;

    @Lob
    @Basic(optional=true)
    @Column(name="BIN_CONTENT", nullable=true)
    private byte[] content;

    @Basic(optional=false)
    @Column(name="ID_ATTACHMENT_STATUS", nullable=false)
    private Integer status = Integer.valueOf(0);

    public int getStatus()
    {
        return this.status.intValue();
    }

    public boolean isSetStatus()
    {
        return this.status != null;
    }

    public void setStatus(int status)
    {
        this.status = Integer.valueOf(status);
    }

    public int getType()
    {
        return this.type.intValue();
    }

    public boolean isSetType()
    {
        return this.type != null;
    }

    public void setType(int type)
    {
        this.type = Integer.valueOf(type);
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

    public String getName()
    {
        return this.name;
    }

    public boolean isSetName()
    {
        return this.name != null;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public boolean isSetContentType()
    {
        return this.contentType != null;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public InputStream getContent()
    {
        return this.content != null ? new BufferedInputStream(new ByteArrayInputStream(this.content)) : null;
    }

    public boolean isSetContent()
    {
        return this.content != null;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }
}

