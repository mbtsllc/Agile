package com.mars.util;

public interface Logger {
	/**
	    * Log a message with severity DEBUG.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    */
	    public void logDebug( String methodName, int errorCode, String message );
  
	    /**
	    * Log a message and stack trace with severity DEBUG.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    * @param t Exception stack trace to log.
	    */
	    public void logDebug( String methodName, int errorCode,
	                          String message, Throwable t );

	    /**
	    * Log a message with severity INFO.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    */
	    public void logInfo( String methodName, int errorCode, String message );

	    /**
	    * Log a message and stack trace with severity INFO.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    * @param t Exception stack trace to log.
	    */
	    public void logInfo( String methodName, int errorCode,
	                         String message, Throwable t );

	    /**
	    * Log a message severity WARN.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    */
	    public void logWarn( String methodName, int errorCode, String message );

	    /**
	    * Log a message and stack trace with severity WARN.
	    *
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    * @param t Exception stack trace to log.
	    */
	    public void logWarn( String methodName, int errorCode,
	                         String message, Throwable t );

	    /**
	    * Log a message with severity ERROR.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    */
	    public void logError( String methodName, int errorCode, String message );

	    /**
	    * Log a message and stack trace with severity ERROR.
	    * @param methodName The name of the method where this call originated from.
	    * @param errorCode Event identifier.
	    * @param message Description of event.
	    * @param t Exception stack trace to log.
	    */
	    public void logError( String methodName, int errorCode,
	                          String message, Throwable t );

	   /**
	    * Log a message severity DEBUG. Outputs an error code of 0 and a message
	    * string "Entering".
	    * @param methodName The name of the method where this call originated from.
	    */
	     public void logMethodBegin( String methodName);

	    /**
	    * Log a message severity DEBUG. Outputs an error code of 0 and a message
	    * string "Leaving".
	    * @param methodName The name of the method where this call originated from.
	    */
	    public void logMethodEnd( String methodName);

	    /**
	    * Returns status to indicate if the DEBUG severity is enabled.
	    * @return true if enabled false is disabled.
	    */
	    public boolean isDebugEnabled();

	    /**
	    * Returns status to indicate if the INFO severity is enabled.
	    * @return true if enabled false is disabled.
	    */
	    public boolean isInfoEnabled();

	    /**
	    * Returns the state of this Logger.
	    * @return String representation of Logger state.
	    */
	    public String toString();
}
