package com.data.permissions.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class ClassUtil implements PackageScanner{

     private static Logger logger = LoggerFactory.getLogger(ClassUtil.class);
     private String basePackage;
     private static ClassLoader cl;


      /**
        * 初始化
        * @param basePackage
      */
      public ClassUtil(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
      }
      public ClassUtil(String basePackage, ClassLoader cl) {
        this.basePackage = basePackage;
        this.cl = cl;
      }



    public static Set<String> scanPackageByAnnotation(String basePackage) throws IOException {
        return doScan(basePackage, new HashSet<String>());
    }

    /**
    *doScan函数
    * @param basePackage
    * @param nameList
    * @return
    * @throws IOException
    */
    private static Set<String> doScan(String basePackage, Set<String> nameList) throws IOException {
        String splashPath = StringUtil.dotToSplash(basePackage);
        URL url = cl.getResource(splashPath);   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        String filePath = StringUtil.getRootPath(url);
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个JAR包", filePath);
            }
            names = readFromJarFile(filePath, splashPath);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个目录", filePath);
            }
            names = readFromDirectory(filePath);
        }
        for (String name : names) {
            if (isClassFile(name)) {
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                doScan(basePackage + "." + name, nameList);
            }
        }
        if (logger.isDebugEnabled()) {
            for (String n : nameList) {
                logger.debug("找到{}", n);
            }
        }
        return nameList;
    }

     private static List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("从JAR包中读取类: {}", jarPath);
        }
        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();
        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
             if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                 nameList.add(name);
             }
             entry = jarIn.getNextJarEntry();
         }
         return nameList;
     }


     private static List<String> readFromDirectory(String path) {
            File file = new File(path);
              String[] names = file.list();

              if (null == names) {
                  return null;
              }

              return Arrays.asList(names);
     }

    private static boolean isClassFile(String name) {
              return name.endsWith(".class");
    }


    private static boolean isJarFile(String name) {
             return name.endsWith(".jar");
    }

    @Override
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        return null;
    }


     private static String toFullyQualifiedName(String shortName, String basePackage) {
             StringBuilder sb = new StringBuilder(basePackage);
             sb.append('.');
             sb.append(StringUtil.trimExtension(shortName));
             //打印出结果
            System.out.println(sb.toString());
             return sb.toString();
    }
}
