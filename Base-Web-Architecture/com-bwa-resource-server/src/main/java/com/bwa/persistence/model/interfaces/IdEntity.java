package com.bwa.persistence.model.interfaces;

import java.io.Serializable;

public interface IdEntity<ID extends Serializable>
{
    ID getId();

    boolean isSetId();
}