package com.lequ.common.util;


import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class StartupLogger
{
	 private static final Logger LOG = LoggerFactory.getLogger(StartupLogger.class);
	  private static final String NEW_LINE = System.getProperty("line.separator");
	  
	  public static void printStartingMessage()
	  {
	    LogMsgBuilder logMsg = new LogMsgBuilder();
	    logMsg.outputHeader("Environment");
	    


	    logMsg.outputProperty("Java Version", System.getProperty("java.version", "unknown??") + " - " + System.getProperty("java.vendor", "unknown??"));
	    logMsg.outputProperty("Current Working Directory", System.getProperty("user.dir", "unknown??"));
	    logMsg.outputProperty("Heap Memory", String.format("Max: %dMB, Committed: %dMB, Free: %dMB, Used: %dMB", new Object[] { Long.valueOf(SystemInfoUtil.getTotalHeapMemoryMax() / 1048576L), Long.valueOf(SystemInfoUtil.getTotalHeapMemoryCommitted() / 1048576L), Long.valueOf(SystemInfoUtil.getTotalHeapMemoryFree() / 1048576L), Long.valueOf(SystemInfoUtil.getTotalHeapMemoryUsed() / 1048576L) }));
	    for (String[] memoryPool : SystemInfoUtil.getMemoryPools()) {
	      logMsg.outputProperty("Memory Pool " + memoryPool[0], memoryPool[1]);
	    }
	    logMsg.outputHeader("Java System Properties");
	    for (Map.Entry<String, String> sysProp : SystemInfoUtil.getSystemProperties().entrySet()) {
	      logMsg.outputProperty((String)sysProp.getKey(), (String)sysProp.getValue());
	    }
	    LOG.info(logMsg.toString());
	  }
	  
	  private static class LogMsgBuilder
	  {
	    StringBuilder logMsg = new StringBuilder();
	    
	    public LogMsgBuilder outputHeader(String header)
	    {
	      this.logMsg.append(StartupLogger.NEW_LINE).append("___  ").append(header).append("  ___").append(StartupLogger.NEW_LINE);
	      return this;
	    }
	    
	    public LogMsgBuilder outputProperty(String name, String value)
	    {
	      this.logMsg.append(" ").append(name).append(" -> ").append(value).append(StartupLogger.NEW_LINE);
	      return this;
	    }
	    
	    public String toString()
	    {
	      return this.logMsg.toString();
	    }
	  }
}
