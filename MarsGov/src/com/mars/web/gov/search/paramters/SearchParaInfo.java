package com.mars.web.gov.search.paramters;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.search.SearchDataType;

/**
 * Class contains search parameters and type
 * @author  tiger
 * @version Demo
 * @date    Jun 25, 2015
 */
public class SearchParaInfo {
	private static Logger logger = LoggerFactory.getLogger(SearchParaInfo.class.getName()) ;
	
	/** Index Name used for search **/
	private String searchIndexForFDA;
	/** Type of searching Index Mode **/
	private SearchDataType searchDataType ;
	/** Display information **/
	private String searchDisplayIndex ;
	/** is Functionality or Business Index. 0, --business, 1 functionality, 2, Index and count mode **/
	private int searchFunctionalityType = 0 ;
	private int searchIndex = 0;
		
	private Object CurrentValue ;
	
	
	public SearchParaInfo(String indexName,SearchDataType dataType, String strDisplayIndex){
		this.setSearchDataType(dataType);
		this.setSearchDisplayIndex(strDisplayIndex);
		this.setSearchIndexForFDA(indexName);
	}
	
	public SearchParaInfo(String indexName,SearchDataType dataType, String strDisplayIndex, int iSearchFuncType, int iSearchIndex){
		this.setSearchDataType(dataType);
		this.setSearchDisplayIndex(strDisplayIndex);
		this.setSearchIndexForFDA(indexName);
		this.setSearchFunctionalityType(iSearchFuncType);
		this.setSearchIndex(iSearchIndex);		
	}


	public String getSearchIndexForFDA() {
		return searchIndexForFDA;
	}


	public void setSearchIndexForFDA(String searchIndexForFDA) {
		this.searchIndexForFDA = searchIndexForFDA;
	}


	public SearchDataType getSearchDataType() {
		return searchDataType;
	}


	public void setSearchDataType(SearchDataType searchDataType) {
		this.searchDataType = searchDataType;
	}


	public String getSearchDisplayIndex() {
		return searchDisplayIndex;
	}


	public void setSearchDisplayIndex(String searchDisplayIndex) {
		this.searchDisplayIndex = searchDisplayIndex;
	}


	public Object getCurrentValue() {
		return CurrentValue;
	}


	public void setCurrentValue(Object currentValue) {
		if (currentValue==null) {
			CurrentValue = null ;
			return ;
		}
		if (searchDataType==SearchDataType.ST_Number){
			try{
				CurrentValue = new Integer(Integer.parseInt(currentValue.toString())) ;
			}catch(Exception e){
				logger.logError("setCurrentValue", -1, String.format("Value should be an Integer, but it is:[%s]", currentValue.toString()));
				CurrentValue = null ;
			}
		}else			
			CurrentValue = currentValue;
	}


	public int getSearchFunctionalityType() {
		return searchFunctionalityType;
	}


	public void setSearchFunctionalityType(int searchFunctionalityType) {
		this.searchFunctionalityType = searchFunctionalityType;
	}

	public int getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(int searchIndex) {
		this.searchIndex = searchIndex;
	}
}
