package com.mars.fda;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;


/**
 * Provide basic methods and properties. 
 * @author  tiger
 * @version Demo
 * @date    Jun 20, 2015
 */
public class SeachEngineBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(SeachEngineBase.class.getName());
	
	protected FDASearchTypeEnums CurrentSearchType ;
	
	public FDASearchTypeEnums getCurrentSearchType() {
		return CurrentSearchType;
	}

	public void setCurrentSearchType(FDASearchTypeEnums currentSearchType) {
		CurrentSearchType = currentSearchType;
	}
	
	

	
}
