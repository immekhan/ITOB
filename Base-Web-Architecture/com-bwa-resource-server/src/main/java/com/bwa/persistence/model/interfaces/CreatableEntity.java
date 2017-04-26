package com.bwa.persistence.model.interfaces;

import java.util.Date;

public interface CreatableEntity
{
    Long getCreator();

    boolean isSetCreator();

    void setCreator(Long paramLong);

    Date getCreationDate();

    boolean isSetCreationDate();

    void setCreationDate(Date paramDate);
}
