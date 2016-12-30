package ata.config;

import ata.util.DateConverter;
import ata.util.TimestampConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
    registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
    registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(31556926);
    registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/").setCachePeriod(31556926);
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    stringHttpMessageConverter.setWriteAcceptCharset(false);
    converters.add(0, stringHttpMessageConverter);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }


  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new DateConverter());
    registry.addConverter(new TimestampConverter());
  }

  @Bean
  public InternalResourceViewResolver getInternalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
}
