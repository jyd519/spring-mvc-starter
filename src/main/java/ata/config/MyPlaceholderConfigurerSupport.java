package ata.config;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.util.Properties;

/*
* Add properties into Spring Environment
* */
public class MyPlaceholderConfigurerSupport extends PropertySourcesPlaceholderConfigurer {

  private ConfigurableEnvironment environment;

  public void setEnvironment(Environment environment) {
    super.setEnvironment(environment);
    this.environment = (ConfigurableEnvironment)environment;
  }

  protected Properties mergeProperties() throws IOException {
    Properties dummy = new Properties();
    environment.getPropertySources().addLast(new PropertiesPropertySource(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME
        , super.mergeProperties()));

    return dummy;
  }
}
