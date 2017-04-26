package com.bwa.persistence.model.interfaces;

import java.util.Date;

public interface UpdatableEntity
{
    Long getLastUpdater();

    boolean isSetLastUpdater();

    void setLastUpdater(Long paramLong);

    Date getLastUpdate();

    boolean isSetLastUpdate();

    void setLastUpdate(Date paramDate);
}