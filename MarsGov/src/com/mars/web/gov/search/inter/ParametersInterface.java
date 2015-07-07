package com.mars.web.gov.search.inter;

import javax.servlet.http.HttpServletRequest;

public interface ParametersInterface {

	/**
	 * Get Parameters information from HttpServletRequest.
	 * @param request HttpServletRequest, contains data from Client URL
	 */
	void ParaseRequest(HttpServletRequest request);
	
	/**
	 * To check Whether parameter values from Client are right or not.
	 * @return True or False.
	 */
	boolean CheckParameters() ;  
	
	/**
	 * Different Searching mode need different Paramters set. All inherited class 
	 * should tell what kind of parameter what the class accept.
	 */
	void InitParameterInfo() ;
	
	/**
	 * Build Searching URL so that server can get data.
	 * @return
	 */
	String BuildSearchURL() ;
}
