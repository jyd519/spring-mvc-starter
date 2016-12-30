package ata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 文件帮助类
 */
public class FileUtil {

  private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

  /**
   * 删除单个文件
   *
   * @param pathList 被删除文件的文件名
   * @return 单个文件删除成功返回true，否则返回false
   */
  public boolean deleteFile(ArrayList<File> pathList) {
    boolean flag = false;
    File file = null;
    if (pathList != null) {
      //删除文件
      for (int i = 0; i < pathList.size(); i++) {
        file = pathList.get(i);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
          if (file.getName().equals("skin.zip")) {
            continue;
          }
          file.delete();
          flag = true;
        } else if (file.isDirectory()) {
          file.delete();
          flag = true;
        }

      }
    }
    return flag;
  }

  /**
   * 重载
   *
   * @param path
   * @return
   */
  public static boolean deleteFile(String path) {
    boolean flag = false;
    File file = new File(path);
    // 路径为文件且不为空则进行删除
    if (file.isFile() && file.exists()) {
      flag = file.delete();
    }
    return flag;
  }

  public static boolean deleteDirectory(String sPath) {
    File dirFile = new File(sPath);
    return deleteFile1(dirFile);
  }

  private static Boolean deleteFile1(File file) {
    Boolean bo = false;
    try {
      if (file.exists()) {
        //判断文件是否存在
        if (file.isFile()) {
          //判断是否是文件
          file.delete();
          //delete()方法 你应该知道 是删除的意思;
        } else if (file.isDirectory()) {
          //否则如果它是一个目录
          File files[] = file.listFiles();
          //声明目录下所有的文件files[];
          for (int i = 0; i < files.length; i++) {
            //遍历目录下所有的文件
            deleteFile1(files[i]);
            //把每个文件 用这个方法进行迭代
          }
        }
        bo = file.delete();
      } else {
        System.out.println("所删除的文件不存在！" + '\n');
        bo = false;
        return false;
      }
    } catch (Exception e) {
      System.out.println("所删除的文件出错：" + e.getMessage());
      bo = false;
    }
    return bo;
  }

  /**
   * 删除目录（文件夹）以及目录下的文件
   *
   * @param sPath 被删除目录的文件路径
   * @return 目录删除成功返回true，否则返回false
   */
  public static boolean deleteDirectory(String sPath, Boolean delDirectory) {
    boolean flag = false;
    //如果sPath不以文件分隔符结尾，自动添加文件分隔符
    if (!sPath.endsWith(File.separator)) {
      sPath = sPath + File.separator;
    }
    File dirFile = new File(sPath);

    //如果dir对应的文件不存在，或者不是一个目录，则退出
    if (!dirFile.exists() || !dirFile.isDirectory()) {
      return false;
    }
    flag = true;
    //删除文件夹下的所有文件(包括子目录)
    File[] files = dirFile.listFiles();
    if (null != files && files.length > 0) {
      for (int i = 0; i < files.length; i++) {
        //删除当前目录
        if (files[i].isFile()) {
          if (!delDirectory) {
            String supp = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);
            String pix = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
            if ((supp.equals("zip") && !pix.equals("skin")) || "session.xml".equals(files[i].getName())) {
              continue;
            } else {
              flag = deleteFile(files[i].getAbsolutePath());
              if (!flag) break;
            }
          } else {
            flag = deleteFile(files[i].getAbsolutePath());
            if (!flag) break;
          }
        } //删除子目录
        else {
          flag = deleteDirectory(files[i].getAbsolutePath(), delDirectory);
          if (!flag) break;
        }
      }
    }
    if (files != null && delDirectory != null && !delDirectory) {
      files = dirFile.listFiles();
      if (files.length == 0) {
        flag = dirFile.delete();//删除空文件夹
        return flag;
      }
    } else {
      flag = dirFile.delete();//删除空文件夹
      return flag;
    }
    return true;
  }

  //解压后，获取对应的文件

  /**
   * 复制单个文件(原名复制)
   *
   * @param oldPathFile 准备复制的文件源
   * @param targetPath 拷贝到新绝对路径带文件名(注：目录路径需带文件名)
   * @return
   */
  public static void CopySingleFileTo(String oldPathFile, String targetPath) {
    InputStream inStream = null;
    FileOutputStream fs = null;
    try {
      //int bytesum = 0;
      int byteread = 0;
      File oldfile = new File(oldPathFile);
      String targetfile = targetPath + File.separator + oldfile.getName();
      if (oldfile.exists()) { //文件存在时
        inStream = new FileInputStream(oldPathFile); //读入原文件
        fs = new FileOutputStream(targetfile);
        byte[] buffer = new byte[1024];
        while ((byteread = inStream.read(buffer)) != -1) {
          // bytesum += byteread; //字节数 文件大小
          fs.write(buffer, 0, byteread);
        }
      }
    } catch (Exception e) {
      logger.error("CopySingleFileTo error", e);
    }
  }

  public static String getFileExtension(String filename) {
    return getFileExtension(new File(filename));
  }

  public static String getFileExtension(File file) {
    String name = file.getName();
    int dotIndex = name.lastIndexOf(".");
    return dotIndex == -1 ? "" : name.substring(dotIndex + 1);
  }
}
