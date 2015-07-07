package com.mars.util;


import java.io.*;
import java.util.*;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;


public class LoggerFactory
{
    /**
     * Logger mapp hastable.
     */
    private static Hashtable loggerMap = new Hashtable();

    /** 
     * private _ctor to disable user creation of this object.
     */
    private LoggerFactory(){}

   
    public static Logger getLogger( String categoryName )
    {
        Logger logger = null;

        if(categoryName!=null && categoryName.length()>0 )
        {
            synchronized(loggerMap)
            {
                Object obj = loggerMap.get(categoryName);

                if(obj == null)
                {
                    logger = new LoggerImpl(categoryName);
                    loggerMap.put(categoryName, logger);
                }
                else if(obj instanceof Logger)
                {
                    logger = (Logger)obj;
                }
             }
        }

        if(logger==null)
        {
            LogLog.error("LoggerFactory:getLogger Could not return Logger object for "
                     +categoryName);
        }

        return logger;
    }

   
    protected static Logger getLogger( java.lang.Class pl )
    {
        Logger logger = null;
        String categoryName = pl.getClass().getName();
        if(categoryName!=null && categoryName.length()>0 )
        {
            synchronized(loggerMap)
            {
                Object obj = loggerMap.get(categoryName);

                if(obj == null)
                {
                    logger = new LoggerImpl(categoryName);
                    loggerMap.put(categoryName, logger);
                }
                else if(obj instanceof Logger)
                {
                    logger = (Logger)obj;
                }
             }
        }

        if(logger==null)
        {
            LogLog.error("LoggerFactory:getLogger Could not return Logger object for "
                     +categoryName);
        }

        return logger;
    }

   
    public static void configure( String propsFile )
    {
        Properties props = new Properties();
        ClassLoader cl = LoggerFactory.class.getClassLoader();
        InputStream in = null;
        if(cl != null)
        {
            in = cl.getResourceAsStream(propsFile);
        }
        else
        {
            System.out.println("LoggerFactory:configure - Using bootstrap ClassLoader");
            in = ClassLoader.getSystemResourceAsStream(propsFile);
        }
        if( in != null)
        {
            synchronized(in)
            {
                try
	        {
                    props.load(in);
	            in.close();
	        }
	        catch(IOException e)
	         {
	           return;
	         }
      }
          PropertyConfigurator.configure(props);
      }
      else
      {
        System.out.println("LoggerFactory:configure - getResourceAsStream failed for " +propsFile);
      }
    }


    public String toString()
    {
        return loggerMap.toString();
    }
}//end LoggerFactory class