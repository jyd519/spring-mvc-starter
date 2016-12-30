package ata.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebAppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
      Properties props = new Properties();
      InputStream strm = getClass().getClassLoader().getResourceAsStream("log4j-no-autoload.properties");
      try {
        props.load(strm);
      } catch (IOException e) {
        throw new Error("can't load logging config file", e);
      } finally {
        try {
          strm.close();
        } catch (IOException e) {
        }
      }
      String appName = servletContext.getInitParameter("app.name");
      String contextPath = servletContext.getContextPath();
      if (contextPath.startsWith("/")) {
        contextPath = contextPath.substring(1);
      }

      //log4j properties
      props.put("app.name", appName != null? appName : contextPath.toLowerCase());
      props.put("app.contextPath", contextPath);
      props.put("app.logFileName", contextPath.isEmpty() ? "root" : contextPath.toLowerCase());
      PropertyConfigurator.configure(props);
  }
}