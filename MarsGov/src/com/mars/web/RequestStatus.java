package com.mars.web;

import net.sf.json.JSONObject;

/**
 * Request action information.
 * @author  tiger
 * @version Demo
 * @date    Jun 21, 2015
 */
public class RequestStatus {
	private String CurrentStatus ;
	private String InformationToDisplay ;
	 
	public String getCurrentStatus() {
		return CurrentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		CurrentStatus = currentStatus;
	}
	public String getInformationToDisplay() {
		return InformationToDisplay;
	}
	public void setInformationToDisplay(String informationToDisplay) {
		InformationToDisplay = informationToDisplay;
	}  
	
	public String generateJSON(){
		return JSONObject.fromObject(this).toString() ;
	}
}
