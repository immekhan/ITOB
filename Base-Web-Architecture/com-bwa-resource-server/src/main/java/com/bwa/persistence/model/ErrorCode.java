package com.bwa.persistence.model;

import com.bwa.persistence.model.common.IdEntry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="ITOB_ERROR_CODES")
@AttributeOverride(name="id", column=@Column(name="ID_ERROR_CODE", length=6))
public class ErrorCode  extends IdEntry<Long>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional=false)
    @Column(name="ID_ERROR_LEVEL", nullable=false,length = 80)
    private String level;

    @Basic(optional=false)
    @Column(name="STR_INFORMATION", nullable=false,length = 200)
    private String info;

    public ErrorCode(Long idCreator){
        this.setCreationDate(new Date());
        this.setCreator(idCreator);
    }

    public ErrorCode(){
        this.setCreationDate(new Date());
    }

    public boolean isSetLevel() {
        return level != null;
    }

    public boolean isSetInfo() {
        return info != null;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
