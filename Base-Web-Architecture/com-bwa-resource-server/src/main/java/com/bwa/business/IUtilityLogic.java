package com.bwa.business;

import com.bwa.persistence.model.OrgUnit;

public interface IUtilityLogic {

    /**
     * @param code
     * @param params
     * @return exception message fetching from DB and formatting
     */
    String fetchExceptionMsg(int code, Object[] params);

    /**
     * @param idOrgUnit
     * @return OrgUnit Object after fetching from DB based on ID
     */
    OrgUnit fetchOrgUnit(String idOrgUnit);
}
