package com.bwa.persistence.repository;

import com.bwa.persistence.model.OrgUnit;

/**
 * Created by Hassnan.Ali on 5/4/2017.
 */
public interface OrgUnitRepository extends BaseRepository<OrgUnit,String> {

    OrgUnit findByName(String name);
}
