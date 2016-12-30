package ata.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @Autowired
  Environment env;

  public String hello(String name) {
    return "Hello " + name + env.getProperty("abc", "");
  }
}
