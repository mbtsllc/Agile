package com.mars.util;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

/**
 * Logger class using Log4j. 
 * Methods are provided.
 * @author  tiger
 * @version Demo
 * @date    Jun 22, 2015
 */
public class LoggerImpl implements Logger {
	/** Entering. */
    private static final String ENTERING = CommonConstants.ENTERING.getValue();
    /** Leaving. */
    private static final String LEAVING = CommonConstants.LEAVING.getValue();
    /** Error Code. */
    private static final String ERRORCODE =CommonConstants.ERROR_CODE_TEXT.getValue();
    /** dash. */
    private static final char DASH =  '-';
    /** Space. */
    private static final char SPACE = ' ';
    /** Colon. */
    private static final char COLON = ':';
    /** Left braces. */
    private static final char LEFTBRACE='{';
    /** right brace. */
    private static final char RIGHTBRACE='}';
    /** Category. */
    private Category category;

    /**
    * Obtains an org.apache.log4j.Category instance.
    * @param categoryName The name of the category associated with the Category
    * instance.
    */
    @SuppressWarnings("deprecation")
	protected LoggerImpl(String categoryName)
    {
    	
        //category = Category.getInstance( categoryName );
    	category = org.apache.log4j.Logger.getInstance(categoryName) ;
        
    }

    /** 
     * private _ctor to disable user creation of this object.
     */
    private LoggerImpl(){}

    /**
    * Log a message with severity DEBUG.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    */
    public void logDebug( String methodName, int errorCode, String message )
    {
        if(category.isDebugEnabled())
        {
            category.debug(buildMsgString( methodName, errorCode, message ));
        }
    }

    /**
    * Log a message and stack trace with severity DEBUG.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    * @param t Exception stack trace to log.
    */
    public void logDebug( String methodName, int errorCode, String message, Throwable t )
    {
        if(category.isDebugEnabled())
        {
            category.debug(buildMsgString( methodName, errorCode, message ), t);
        }
    }

    /**
    * Log a message with severity INFO.
     * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    */
    public void logInfo( String methodName, int errorCode, String message )
    {
        if(category.isInfoEnabled())
        {
            category.info(buildMsgString( methodName, errorCode, message ));
        }
    }

    /**
    * Log a message and stack trace with severity INFO.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    * @param t Exception stack trace to log.
    */
    public void logInfo( String methodName, int errorCode, String message, Throwable t )
    {
        if(category.isInfoEnabled())
        {
            category.info(buildMsgString( methodName, errorCode, message ), t);
        }
    }

    /**
    * Log a message severity WARN.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    */
    public void logWarn( String methodName, int errorCode, String message )
    {
        if(category.isDebugEnabled())
        {
            category.warn(buildMsgString( methodName, errorCode, message ));
        }
    }

    /**
    * Log a message and stack trace with severity WARN.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    * @param t Exception stack trace to log.
    */
    public void logWarn( String methodName, int errorCode, String message, Throwable t )
    {
        if(category.isDebugEnabled() )
        {
            category.warn(buildMsgString( methodName, errorCode, message), t );
        }
    }

   /**
    * Log a message with severity ERROR.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    */
    public void logError( String methodName, int errorCode, String message )
    {
        if(category.isDebugEnabled())
        {
            category.error(buildMsgString( methodName, errorCode, message ));
        }
    }

    /**
    * Log a message and stack trace with severity ERROR.
    * @param methodName The name of the method where this call originated from.
    * @param errorCode Event identifier.
    * @param message Description of event.
    * @param t Exception stack trace to log.
    */
    public void logError( String methodName, int errorCode, String message, Throwable t )
    {
        if(category.isDebugEnabled())
        {
            category.error(buildMsgString( methodName, errorCode, message), t );
        }
    }

    /**
    * Log a message severity DEBUG. Outputs an error code of 0 and a message string "Entering".
    * @param methodName The name of the method where this call originated from.
    */
    public void logMethodBegin( String methodName)
    {
        if(category.isDebugEnabled()==true)
        {
            category.debug(buildMsgString( methodName, 0, ENTERING ));
        }
    }

     /**
     * Log a message severity DEBUG. Outputs an error code of 0 and a message string "Leaving".
     * @param methodName The name of the method where this call originated from.
     */
     public void logMethodEnd( String methodName)
     {
        if(category.isDebugEnabled()==true)
        {
            category.debug(buildMsgString( methodName, 0, LEAVING ));
        }
     }

     /**
     * Returns status to indicate if the DEBUG severity is enabled.
     * @return true if enabled false is disabled.
     */
     public boolean isDebugEnabled()
     {
        return category.isDebugEnabled();
     }

     /**
     * Returns status to indicate if the INFO severity is enabled.
     * @return true if enabled false is disabled.
     */
     public boolean isInfoEnabled()
     {
        return category.isInfoEnabled();
     }

     /**
     * Returns the state of this Logger.
     * @return String representation of Logger state.
     */
     public String toString()
     {
        return new StringBuffer().append("Category Name:")
                                 .append(category.getName()).toString();
     }

     /**
     * Formats message string for Logger output.
     * @param methodName The name of the method where the original log message originated from.
     * @param errorCode Event identifier.
     * @param message Description of event.
     * @return Formatted String.
     */
     private String buildMsgString(String methodName, int errorCode, String message)
     {
        StringBuffer buff = new StringBuffer();
        buff.append(methodName);
        buff.append(SPACE);
        buff.append(LEFTBRACE);
        buff.append(ERRORCODE);
        buff.append(COLON);
        buff.append(errorCode);
        buff.append(RIGHTBRACE);
        buff.append(DASH);
        buff.append(message);
        return buff.toString();
     }
}
