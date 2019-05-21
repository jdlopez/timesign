package es.jdl.entrysign.config;

import com.googlecode.objectify.ObjectifyService;
import es.jdl.entrysign.domain.EntrySign;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class AppConfig implements ServletContextListener {

    // Public constructor is required by servlet spec
    public AppConfig() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        ObjectifyService.init();
        ObjectifyService.register(EntrySign.class);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
