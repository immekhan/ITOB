package com.bwa.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Hassnan.Ali on 2/15/2017.
 */
public class WebConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{PersistenceContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

            return new Class[] { WebConfiguration.class };}

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
//another method to run initialize this the WebConfigurationInitializer
/*public class WebConfigurationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext acAppContext=new AnnotationConfigWebApplicationContext ();
        acAppContext.register(WebConfiguration.class);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(acAppContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}*/
