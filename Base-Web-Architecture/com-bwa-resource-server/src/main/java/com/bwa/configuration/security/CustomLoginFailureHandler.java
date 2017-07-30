package com.bwa.configuration.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    private static final Logger LOG = Logger.getLogger(CustomLoginFailureHandler.class);

    public CustomLoginFailureHandler(){
        super();
        setRedirectStrategy(new NoRedirectStrategy());
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        try {
            response.getWriter().write("Username/Password not recognized.");
            response.getWriter().flush();
        } catch (IOException e) {
            LOG.info("Error : ",e);
        }
    }

    protected class NoRedirectStrategy implements RedirectStrategy {

        @Override
        public void sendRedirect(HttpServletRequest request,
                                 HttpServletResponse response, String url) throws IOException {
            // no redirect

        }

    }

}