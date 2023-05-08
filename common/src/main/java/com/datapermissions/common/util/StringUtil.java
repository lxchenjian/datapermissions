package com.datapermissions.common.util;


import java.net.URL;

public class StringUtil {

    /**
     * 1、URL的提取、转化
     */



    private StringUtil() {

    }

    /**
     * 作用：获取  最后一个:  右边的字符串
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
      public static String getRootPath(URL url) {
         String fileUrl = url.getFile();
          int pos = fileUrl.indexOf('!');

             if (-1 == pos) {
                 return fileUrl;
              }

            return fileUrl.substring(5, pos);
        }

    /**
     * 作用：. -> /
     * "cn.fh.lightning" -> "cn/fh/lightning"
     * @param name
     * @return
      */
        public static String dotToSplash(String name) {
          return name.replaceAll("\\.", "/");
        }

     /**
      * 作用：去掉 .class
      * "Apple.class" -> "Apple"
     */
     public static String trimExtension(String name) {
       int pos = name.indexOf('.');
      if (-1 != pos) {
                return name.substring(0, pos);
           }

        return name;
         }

    /**
      * 作用：
      * /application/home -> /home
      * @param uri
      * @return
    */
       public static String trimURI(String uri) {
            String trimmed = uri.substring(1);
            int splashIndex = trimmed.indexOf('/');

          return trimmed.substring(splashIndex);
          }
}
