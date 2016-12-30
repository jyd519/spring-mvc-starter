package ata.web;

import ata.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

  @Autowired
  TestService testService;

  @Autowired
  Environment env;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("test-chs")
  @ResponseBody
  public String testChs() {
    return "中文";
  }

  @GetMapping("test-json")
  @ResponseBody
  public Map<String, Object> testJson() {
    Map<String, Object> test = new HashMap<>();

    test.put("key1", 3232);
    test.put("key2", "中文");
    test.put("key3", new Date());
    test.put("key4", testService.hello("world"));
    test.put("key5", env.resolvePlaceholders("${abc}"));
    test.put("key6", env.getProperty("abc", ""));

    return test;
  }
}
