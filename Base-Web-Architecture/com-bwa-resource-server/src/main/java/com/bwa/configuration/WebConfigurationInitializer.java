package com.bwa.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


public class WebConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{PersistenceContext.class,QuartzJobSchedulerConf.class};
        return new Class[]{PersistenceContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

            return new Class[] { WebConfiguration.class };}

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter()};
        return singleton;
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
