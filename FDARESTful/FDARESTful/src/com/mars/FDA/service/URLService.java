package com.mars.FDA.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.properties.DrugSearchPropertiesBean;

@Path("/urlsvc")
public class URLService {
	private static Logger logger=LoggerFactory.getLogger(URLService.class.getName()) ;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetURLPreFix(){
		return DrugSearchPropertiesBean.getInstance().GetFDASearchPattern() ;
	}
}
