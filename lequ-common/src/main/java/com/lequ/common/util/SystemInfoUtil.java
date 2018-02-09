package com.lequ.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.springframework.core.io.Resource;

public class SystemInfoUtil {
	 private static MemoryMXBean memoryMXBean = null;
	  private static RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	  private static List<String> PATH_RELATED_KEYS = new ArrayList();
	  private static List<String> IGNORE_THESE_KEYS = new ArrayList();
	  
	  static
	  {
	    PATH_RELATED_KEYS.add("sun.boot.class.path");
	    PATH_RELATED_KEYS.add("com.ibm.oti.vm.bootstrap.library.path");
	    PATH_RELATED_KEYS.add("java.library.path");
	    PATH_RELATED_KEYS.add("java.endorsed.dirs");
	    PATH_RELATED_KEYS.add("java.ext.dirs");
	    PATH_RELATED_KEYS.add("java.class.path");
	    
	    IGNORE_THESE_KEYS.addAll(PATH_RELATED_KEYS);
	    IGNORE_THESE_KEYS.addAll(PATH_RELATED_KEYS);
	    IGNORE_THESE_KEYS.add("line.separator");
	    IGNORE_THESE_KEYS.add("path.separator");
	    IGNORE_THESE_KEYS.add("file.separator");
	  }
	  
	  public static float getJvmVersion()
	  {
	    String property = System.getProperty("java.specification.version");
	    try
	    {
	      return Float.valueOf(property).floatValue();
	    }
	    catch (Exception e)
	    {
	      throw new IllegalStateException("Invalid JVM version: '" + property + "'. " + e.getMessage());
	    }
	  }
	  
	  public static long getTotalHeapMemoryInit()
	  {
	    return memoryMXBean.getHeapMemoryUsage().getInit();
	  }
	  
	  public static long getTotalHeapMemoryMax()
	  {
	    return memoryMXBean.getHeapMemoryUsage().getMax();
	  }
	  
	  public static long getTotalHeapMemoryCommitted()
	  {
	    return memoryMXBean.getHeapMemoryUsage().getCommitted();
	  }
	  
	  public static long getTotalHeapMemoryUsed()
	  {
	    return memoryMXBean.getHeapMemoryUsage().getUsed();
	  }
	  
	  public static long getTotalHeapMemoryFree()
	  {
	    return getTotalHeapMemoryCommitted() - getTotalHeapMemoryUsed();
	  }
	  
	  public static List<String[]> getMemoryPools()
	  {
	    List<String[]> pools = new ArrayList();
	    List<MemoryPoolMXBean> mxBeans = ManagementFactory.getMemoryPoolMXBeans();
	    for (MemoryPoolMXBean mxBean : mxBeans) {
	      pools.add(new String[] { mxBean.getName().replaceAll("[ ]", ""), mxBean.getUsage().toString() });
	    }
	    return pools;
	  }
	  
	  public static Map<String, String> getSystemProperties()
	  {
	    Properties props = System.getProperties();
	    Map<String, String> properties = new TreeMap(props);
	    properties.keySet().removeAll(IGNORE_THESE_KEYS);
	    for (String key : properties.keySet()) {
	      if (key.indexOf("password") != -1) {
	        properties.put(key, "********");
	      }
	    }
	    return properties;
	  }
	  
	  public static String getLocalAddress()
	  {
	    try
	    {
	      Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
	      ArrayList<String> ipv4Result = new ArrayList();
	      ArrayList<String> ipv6Result = new ArrayList();
	      while (enumeration.hasMoreElements())
	      {
	        NetworkInterface networkInterface = (NetworkInterface)enumeration.nextElement();
	        Enumeration<InetAddress> en = networkInterface.getInetAddresses();
	        while (en.hasMoreElements())
	        {
	          InetAddress address = (InetAddress)en.nextElement();
	          if (!address.isLoopbackAddress()) {
	            if ((address instanceof Inet6Address)) {
	              ipv6Result.add(normalizeHostAddress(address));
	            } else {
	              ipv4Result.add(normalizeHostAddress(address));
	            }
	          }
	        }
	      }
	      if (!ipv4Result.isEmpty())
	      {
	        for (String ip : ipv4Result) {
	          if ((!ip.startsWith("127.0")) && (!ip.startsWith("192.168"))) {
	            return ip;
	          }
	        }
	        return (String)ipv4Result.get(ipv4Result.size() - 1);
	      }
	      if (!ipv6Result.isEmpty()) {
	        return (String)ipv6Result.get(0);
	      }
	      InetAddress localHost = InetAddress.getLocalHost();
	      return normalizeHostAddress(localHost);
	    }
	    catch (SocketException e)
	    {
	      throw new IllegalStateException(e.getMessage(), e);
	    }
	    catch (UnknownHostException e)
	    {
	      throw new IllegalStateException(e.getMessage(), e);
	    }
	  }
	  
	  public static String normalizeHostAddress(InetAddress localHost)
	  {
	    if ((localHost instanceof Inet6Address)) {
	      return "[" + localHost.getHostAddress() + "]";
	    }
	    return localHost.getHostAddress();
	  }
	  
	  public static boolean isLinuxOS()
	  {
	    return System.getProperty("os.name").toLowerCase().indexOf("linux") != -1;
	  }
	  
	  public static boolean isWindowOS()
	  {
	    return System.getProperty("os.name").toLowerCase().indexOf("win") != -1;
	  }
	  
	  public static String getPid()
	  {
	    String name = runtimeMXBean.getName();
	    if ((name != null) && (name.length() > 0) && (name.indexOf("@") != -1)) {
	      return name.split("@")[0];
	    }
	    return null;
	  }
	  
	  public static String getServerPort()
	  {
	    String port = getServerPort("HTTP/1.1", "http");
	    if (StringUtil.isBlank(port)) {
	      port = getServerPort("HTTP/1.1", "https");
	    }
	    if (StringUtil.isBlank(port)) {
	      port = getServerPort("org.apache.coyote.http11.Http11NioProtocol", "http");
	    }
	    if (StringUtil.isBlank(port)) {
	      port = getServerPort("org.apache.coyote.http11.Http11NioProtocol", "https");
	    }
	    if (StringUtil.isBlank(port))
	    {
	      Set<String> portSet = new TreeSet();
	      if (isLinuxOS())
	      {
	        List<String> result = execCmd(new String[] { "/bin/sh", "-c", "netstat -tnpl | grep " + getPid() + "/java | awk '{print $4}'" });
	        for (String line : result) {
	          if (line.indexOf(":ffff:127.0.0.1:") == -1) {
	            if (line.indexOf(":") != -1) {
	              portSet.add(line.substring(line.lastIndexOf(":") + 1, line.length()));
	            }
	          }
	        }
	      }
	      else if (isWindowOS())
	      {
	        List<String> result = execCmd(new String[] { "cmd", "/C", "netstat -aon|findstr " + getPid() });
	        for (String line : result) {
	          if ((line.indexOf("LISTENING") != -1) && (line.indexOf("127.0.0.1:") == -1) && (line.indexOf("0.0.0.0:0") != -1)) {
	            portSet.add(line.substring(line.indexOf("0.0.0.0:") + 8, line.indexOf("0.0.0.0:0")).trim());
	          }
	        }
	      }
	      if (portSet.size() > 0) {
	        port = (String)portSet.iterator().next();
	      }
	    }
	    return port;
	  }
	  
	  public static String getServerPort(String protocol, String scheme)
	  {
	    MBeanServer mBeanServer = null;
	    if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
	      mBeanServer = (MBeanServer)MBeanServerFactory.findMBeanServer(null).get(0);
	    }
	    if (mBeanServer == null) {
	      return null;
	    }
	    Set names = null;
	    try
	    {
	      names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
	    }
	    catch (Exception e)
	    {
	      return null;
	    }
	    if (names != null) {
	      try
	      {
	        Iterator it = names.iterator();
	        ObjectName oname = null;
	        while (it.hasNext())
	        {
	          oname = (ObjectName)it.next();
	          String pvalue = (String)mBeanServer.getAttribute(oname, "protocol");
	          String svalue = (String)mBeanServer.getAttribute(oname, "scheme");
	          if ((protocol.equalsIgnoreCase(pvalue)) && (scheme.equalsIgnoreCase(svalue))) {
	            return ((Integer)mBeanServer.getAttribute(oname, "port")).toString();
	          }
	        }
	      }
	      catch (Exception e)
	      {
	        throw new IllegalStateException(e.getMessage(), e);
	      }
	    }
	    return null;
	  }
	  
	  public static List<String> getLibs()
	  {
	    List<String> libs = new ArrayList();
	    String classPath = SystemInfoUtil.class.getResource("/").getPath();
	    if (classPath.endsWith("/classes/"))
	    {
	      String webInfPath = classPath.substring(0, classPath.length() - "/classes/".length());
	      Resource[] jarResources = ResourceUtil.getResources("file:" + webInfPath + "/lib/*.jar", true);
	      if ((jarResources != null) && (jarResources.length > 0)) {
	        for (Resource jarResource : jarResources) {
	          libs.add(jarResource.getFilename());
	        }
	      }
	    }
	    if (libs.size() == 0)
	    {
	      String wtpDeploy = System.getProperty("wtp.deploy");
	      if (StringUtil.isNotBlank(wtpDeploy))
	      {
	        Resource[] jarResources = ResourceUtil.getResources("file:" + wtpDeploy + "/**/lib/*.jar", true);
	        if ((jarResources != null) && (jarResources.length > 0)) {
	          for (Resource jarResource : jarResources) {
	            libs.add(jarResource.getFilename());
	          }
	        }
	      }
	    }
	    if (libs.size() == 0)
	    {
	      String javaClassPathStr = null;
	      
	      String jettyCfg = System.getProperty("jetty.launcher.configuration");
	      if (StringUtil.isNotBlank(jettyCfg)) {
	        try
	        {
	          String jettyCfgContent = IoUtil.toStringAndClose(new FileInputStream(new File(jettyCfg)));
	          javaClassPathStr = StringUtil.substrBB(jettyCfgContent, "<Set name=\"extraClasspath\">", "</Set>");
	        }
	        catch (IOException localIOException) {}
	      }
	      if (StringUtil.isBlank(javaClassPathStr)) {
	        javaClassPathStr = System.getProperty("java.class.path");
	      }
	      if (StringUtil.isNotBlank(javaClassPathStr))
	      {
	        String[] javaClassPathArray = javaClassPathStr.split(";");
	        for (String javaClassPath : javaClassPathArray) {
	          if (javaClassPath.endsWith(".jar"))
	          {
	            javaClassPath = javaClassPath.replaceAll("\\\\", "/");
	            int i = javaClassPath.lastIndexOf("/");
	            if (i >= 0) {
	              libs.add(javaClassPath.substring(i + 1));
	            } else {
	              libs.add(javaClassPath);
	            }
	          }
	        }
	      }
	    }
	    return libs;
	  }
	  
	  public static String[] getPom()
	  {
	    String[] pom = new String[2];
	    Resource[] resources = null;
	    
	    URL resourceURL = SystemInfoUtil.class.getResource("/");
	    if (resourceURL == null) {
	      return null;
	    }
	    String classPath = resourceURL.getPath();
	    if (classPath.endsWith("/WEB-INF/classes/"))
	    {
	      String webRootPath = classPath.substring(0, classPath.length() - "/WEB-INF/classes/".length());
	      resources = ResourceUtil.getResources("file:" + webRootPath + "/META-INF/maven/**/*", true);
	    }
	    else if (classPath.endsWith("/target/test-classes/"))
	    {
	      String projectTargetRoot = classPath.substring(0, classPath.length() - "/test-classes/".length());
	      resources = ResourceUtil.getResources("file:" + projectTargetRoot + "/m2e-wtp/web-resources/META-INF/maven/**/*", true);
	    }
	    if ((resources == null) || (resources.length == 0))
	    {
	      String wtpDeploy = System.getProperty("wtp.deploy");
	      if (StringUtil.isNotBlank(wtpDeploy)) {
	        resources = ResourceUtil.getResources("file:" + wtpDeploy + "/**/META-INF/maven/**/*", true);
	      }
	    }
	    if ((resources != null) && (resources.length > 0)) {
	      for (Resource resource : resources)
	      {
	        String filename = resource.getFilename();
	        try
	        {
	          if ("pom.properties".equals(filename)) {
	            pom[0] = IoUtil.toStringAndClose(resource.getInputStream());
	          } else if ("pom.xml".equals(filename)) {
	            pom[1] = IoUtil.toStringAndClose(resource.getInputStream());
	          }
	        }
	        catch (Exception localException) {}
	      }
	    }
	    if (pom[0] == null)
	    {
	      Resource envResource = ResourceUtil.getResource("classpath:env.properties");
	      if (envResource != null) {
	        try
	        {
	          Properties envPorperties = new Properties();
	          envPorperties.load(envResource.getInputStream());
	          String[] appName = envPorperties.getProperty("app.name").split("_", 2);
	          String appVersion = envPorperties.getProperty("app.version");
	          
	          pom[0] = ("version=" + appVersion + "\r\ngroupId=" + appName[0] + "\r\nartifactId=" + appName[1]);
	        }
	        catch (IOException localIOException) {}
	      }
	    }
	    if (pom[1] == null) {
	      try
	      {
	        pom[1] = IoUtil.toStringAndClose(new FileInputStream(new File("pom.xml")));
	      }
	      catch (IOException localIOException1) {}
	    }
	    return pom;
	  }
	  
	  public static List<String> execCmd(String[] command)
	  {
	    List<String> result = new ArrayList();
	    
	    Process process = null;
	    BufferedReader reader = null;
	    try
	    {
	      ProcessBuilder processBuilder = new ProcessBuilder(command);
	      processBuilder.redirectErrorStream(true);
	      process = processBuilder.start();
	      
	      reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	      String line = null;
	      while ((line = reader.readLine()) != null) {
	        result.add(line);
	      }
	      process.waitFor();
	    }
	    catch (Exception e)
	    {
	      throw new IllegalStateException(e.getMessage(), e);
	    }
	    finally
	    {
	      IoUtil.closeQuietly(reader);
	      if (process != null) {
	        process.destroy();
	      }
	    }
	    return result;
	  }
	  
	  public static void main(String[] args)
	  {
	    List<String> list = new ArrayList();
	    list.add("(Not all processes could be identified, non-owned process info");
	    list.add("will not be shown, you would have to be root to see it all.)");
	    list.add(":::8080");
	    list.add(":::8019");
	    list.add("::ffff:127.0.0.1:8005");
	    for (String line : list) {
	      if (line.indexOf(":ffff:127.0.0.1:") == -1) {
	        if (line.indexOf(":") != -1) {
	          System.out.println(line.substring(line.lastIndexOf(":") + 1, line.length()));
	        }
	      }
	    }
	  }
}
