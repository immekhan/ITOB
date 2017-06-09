package com.bwa.controllers;

import com.bwa.business.IUtilityLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilityValidation {

    @Autowired
    private IUtilityLogic utilityLogic;

    public boolean isValidOrgUnitId(String idOrgUnit){
        return utilityLogic.fetchOrgUnit(idOrgUnit) !=null;
    }
}
