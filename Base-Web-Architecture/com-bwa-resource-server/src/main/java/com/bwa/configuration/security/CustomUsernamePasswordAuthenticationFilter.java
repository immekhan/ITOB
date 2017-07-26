package com.bwa.configuration.security;

import com.bwa.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //TODO DEFAULT ORGUNIT ID SHOULD BE LOADED THROUGH PROPERTY FILES
    private String orgUnitId= Constants.DEFAULT_ORGUNIT_ID;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        final String orgUnitId = request.getParameter("orgUnitId") ;
        this.orgUnitId = orgUnitId!=null&&!orgUnitId.isEmpty() ? orgUnitId  : Constants.DEFAULT_ORGUNIT_ID;
        request.getSession().setAttribute("orgUnitId", orgUnitId);

        return super.attemptAuthentication(request, response);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {

        String username = request.getParameter(getUsernameParameter());

        final String orgUnitId = request.getParameter(this.orgUnitId).trim();

        String combinedUsername = "";
        if (orgUnitId.length() == 0) {
            combinedUsername = username;
        } else {
            combinedUsername = username + ":" + orgUnitId;
        }
        return combinedUsername;
    }

    public String getOrgUnitId() {
        return orgUnitId;
    }
}