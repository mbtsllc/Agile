package com.mars.util;

/**
 * System constants information
 * @author  Marquis
 * @version Demo
 * @date    Jun 19, 2015
 */ 
public enum CommonConstants {
	/** Entering. */
	ENTERING("Entering"),
	/** Error Code text. */
	ERROR_CODE_TEXT("Error Cod"),
	/** dash. */
	DASH("-"),
	/** Space. */
	SPACE("' '"),
	/** Leaving. */
	LEAVING("Leaving"),
	
	/** configuration files name **/
	PARAMETER_CONFIGFILE("DrugSearchProperties.properties"),
	
	/** Para configuration file information **/
	PARAMETER_SEARCH_PREFIX("SearchParameterClass"),
	
	/** Default search class name           **/
	DEFAULT_SEARCH_CLASS("com.mars.web.gov.search.paramters.impl.DrugParameterImpl"),
	
	/** Error type **/
	ERROR_TYPE_PARAOBJECT_ISNULL("Parameter object is null"),
	
	/** Web request status **/
	WEB_SUCCESS("SUCCESS"),
	WEB_ERROR("ERROR"),
	
	/** Drug Searching Index Information **/
	
	;
	
	private String Value = null ;
	
	CommonConstants(String strValue)
	{
		setValue(strValue) ;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	
	
}
