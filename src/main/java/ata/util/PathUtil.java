package ata.util;

import java.io.File;

/**
 * Created by jiyongdong on 15/5/11.
 */
public class PathUtil {
  public static String join(String... paths) {
    File file = new File(paths[0]);

    for (int i = 1; i < paths.length; i++) {
      file = new File(file, paths[i]);
    }

    return file.getPath();
  }

  //todo
  public static String safeJoin(String... paths) {
    File file = new File(paths[0]);

    for (int i = 1; i < paths.length; i++) {
      file = new File(file, paths[i]);
    }

    return file.getPath();
  }
}
