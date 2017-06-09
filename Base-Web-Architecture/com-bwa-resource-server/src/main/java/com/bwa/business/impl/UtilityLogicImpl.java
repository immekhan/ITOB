package com.bwa.business.impl;


import com.bwa.business.IUtilityLogic;
import com.bwa.persistence.model.ErrorCode;
import com.bwa.persistence.model.OrgUnit;
import com.bwa.persistence.repository.ErrorCodeRepository;
import com.bwa.persistence.repository.OrgUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Optional;

@Component
public class UtilityLogicImpl implements IUtilityLogic {

    @Autowired
    ErrorCodeRepository errorCodeRepository;

    @Autowired
    OrgUnitRepository orgUnitRepository;

    @Override
    public String fetchExceptionMsg(int code, Object[] params) throws EntityNotFoundException{

        Optional<ErrorCode> errorCodeOpt=errorCodeRepository.findOne(new Integer(code).longValue());

        if(errorCodeOpt.isPresent()){

            String errorMsg=errorCodeOpt.get().getInfo();
            if(params!=null&&params.length>0) {
                MessageFormat mf = new MessageFormat(errorMsg);
                errorMsg=mf.format(params);
            }
            return errorMsg;
        }else{
           throw new EntityNotFoundException("Error Code not found: " + code);
        }

    }

    @Override
    public OrgUnit fetchOrgUnit(String idOrgUnit) {

        Optional<OrgUnit> orgUnitOpt = orgUnitRepository.findOne(idOrgUnit);

        if(orgUnitOpt.isPresent()){
            return orgUnitRepository.findOne(idOrgUnit).get();
        }else{
            throw new EntityNotFoundException("Error Organization not found for id : " + idOrgUnit);
        }
    }
}
