package ata.util;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.UUID;

public class StringHelper {
	public static String null2string(Object object){
		if(object==null){
			return "";
		}else{
			return object.toString();
		}
	}

	public  static String GenUUID() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString.toLowerCase();
	}

	public static String formatUUID(String id){
		if (id == null)
			return "";

		if (id.length()==32){
			return id.substring(0,8)+"-"+id.substring(8,12)+"-"+id.substring(12,16)+"-"+id.substring(16,20)+"-"+id.substring(20,32);
		}
		return id;
	}

	public static String null2space(Object object){
		if(object==null){
			return " ";
		}else{
			return object.toString();
		}
	}

	public static String GenPassword(Integer len)
	{
	    UUID uuid = UUID.randomUUID();
	    String randomUUIDString = uuid.toString();
	    String result=randomUUIDString.toLowerCase();
	    result=result.replace("-", "");
	    Random rm = new Random();
	    if (len<4) len=4;
	    if (len>20) len=20;
	    Integer beginIndex=rm.nextInt(result.length()-len);
	    return result.substring(beginIndex, beginIndex+len);
	}

	//输出CDATA格式文本
	public static String formatXmlText(Object object){
		return object==null?"<![CDATA[]]>":"<![CDATA["+object.toString()+"]]>";
	}

	/**
	 *  设置下载文件名，解决文件名中文乱码问题
	 *  @author pitt
	 *  @date   20141112
	 *  @author xumin
   *  @date 20160302
	 * */
	public static String getDownloadFileName(String userAgent, String downloadFileName) throws UnsupportedEncodingException {
		downloadFileName = StringHelper.null2string(downloadFileName);
    userAgent = userAgent.toLowerCase();
    // IE浏览器，只能采用URLEncoder编码
    if (userAgent.indexOf("msie") != -1 || userAgent.indexOf("edge") != -1 || userAgent.indexOf("trident") != -1)
    {
      downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
    }
    // Opera浏览器只能采用filename*
    else if (userAgent.indexOf("opera") != -1)
    {
      //"filename*=UTF-8''" + downloadFileName;
    }
    // Safari浏览器，只能采用ISO编码的中文输出
    else if (userAgent.indexOf("safari") != -1 )
    {
      downloadFileName = new String(downloadFileName.getBytes("UTF-8"),"ISO8859-1");
    }
    // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
    else if (userAgent.indexOf("applewebkit") != -1 )
    {
      downloadFileName = MimeUtility.encodeText(downloadFileName, "UTF8", "B");
    }
    // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
    else if (userAgent.indexOf("mozilla") != -1)
    {
      //rtn = "filename*=UTF-8''" + new_filename;
      downloadFileName = new String(downloadFileName.getBytes("UTF-8"),"ISO8859-1");
    }
    else
      downloadFileName = URLEncoder.encode(downloadFileName, "utf-8");
		return downloadFileName;
	}


	/**
	 *  得到制定长度的随机数字符串
	 *  @author Pitt
	 *  @date   20141215
	 *
	 * */
	public static String getFixLenthString(int strLength) {

	    Random rm = new Random();

	    // 获得随机数
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

	    // 将获得的获得随机数转化为字符串
	    String fixLenthString = String.valueOf(pross);

	    // 返回固定的长度的随机数
	    return fixLenthString.substring(2, strLength + 2);
	}

	/**
	 *  换行符替换
	 *  @author Pitt
	 *  @date   20141231
	 *
	 * */
	public static String enterStrReplace(String str) {

		if("".equals(StringHelper.null2string(str))){
			return "";
		}

	    return str.replaceAll("\r\n", "<br/>").replaceAll("\r", "<br/>").replaceAll("\n", "<br/>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	/**
	 *  职位符剔除
	 *  @author Pitt
	 *  @date   20141231
	 *
	 * */
	public static String bitSymbolStrDel(String str) {

		if("".equals(StringHelper.null2string(str))){
			return "";
		}

	    return str.replaceAll("\\s", " ");
	}


    public static String join(String join, String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        } else if (strings.length == 1) {
            return strings[0];
        } else {
						StringBuilder buffer = new StringBuilder();
            buffer.append(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                buffer.append(join).append(strings[i]);
            }
            return buffer.toString();
        }
    }
}
