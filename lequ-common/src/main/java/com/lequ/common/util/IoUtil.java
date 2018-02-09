package com.lequ.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class IoUtil extends IOUtils
{
	  public static String toStringAndClose(InputStream is)
	    throws IOException
	  {
	    try
	    {
	      return IOUtils.toString(is);
	    }
	    finally
	    {
	      IOUtils.closeQuietly(is);
	    }
	  }
	}
