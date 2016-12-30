package ata.util;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {

  public Properties load(String fileName) {
    Properties prop = new Properties();
    InputStream im = null;
    try {
      im = findFile(fileName);
      prop.load(im);
    } catch (IOException ignore) {
    } finally {
      if (im != null) {
        try {
          im.close();
        } catch (IOException ignore) {
        }
      }
    }
    return prop;
  }

  private InputStream findFile(String fileName) throws FileNotFoundException {
    InputStream im = findInWorkingDirectory(fileName);
    if (im == null) im = findInClasspath(fileName);
    if (im == null) im = findInSourceDirectory(fileName);
    if (im == null) throw new FileNotFoundException(String.format("File %s not found", fileName));
    return im;
  }

  private InputStream findInSourceDirectory(String fileName) {
    try {
      return new FileInputStream("src/main/resources/" + fileName);
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  private InputStream findInClasspath(String fileName) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
  }

  private InputStream findInWorkingDirectory(String fileName) {
    try {
      File file = Paths.get(fileName).toFile();
      if (file.exists()) {
        return new FileInputStream(file);
      }

      return new FileInputStream(System.getProperty("user.dir") + fileName);
    } catch (FileNotFoundException e) {
      return null;
    }
  }

}