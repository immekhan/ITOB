package com.bwa.business.impl;


import com.bwa.business.IUtilityLogic;
import com.bwa.persistence.model.ErrorCode;
import com.bwa.persistence.repository.ErrorCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Optional;

@Component
public class UtilityLogicImpl implements IUtilityLogic {

    @Autowired
    ErrorCodeRepository errorCodeRepository;

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
}
