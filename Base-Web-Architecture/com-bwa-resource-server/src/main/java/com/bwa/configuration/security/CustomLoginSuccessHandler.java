package com.bwa.configuration.security;

import com.bwa.util.CodeConstants;
import com.bwa.util.Constants;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOG = Logger.getLogger(CustomLoginSuccessHandler.class);

    public CustomLoginSuccessHandler(){
        super();
        setRedirectStrategy(new NoRedirectStrategy());
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        try {

            request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_USERNAME
                    ,request.getParameter(Constants.SESSION_ATTRIBUTE_KEY_USERNAME));
            request.getSession().setAttribute(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID,
                    request.getParameter(Constants.SESSION_ATTRIBUTE_KEY_ORG_UNIT_ID));

            response.getWriter().write(CodeConstants.CODE_SUCCESS);
            response.getWriter().flush();
        } catch (IOException e) {
            LOG.info("Error : ",e);
        }

        this.handle(request, response, authentication);
        this.clearAuthenticationAttributes(request);

    }

    protected class NoRedirectStrategy implements RedirectStrategy {

        @Override
        public void sendRedirect(HttpServletRequest request,
                                 HttpServletResponse response, String url) throws IOException {
            // no redirect

        }

    }

}