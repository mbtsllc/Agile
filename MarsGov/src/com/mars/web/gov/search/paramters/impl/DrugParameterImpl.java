package com.mars.web.gov.search.paramters.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.search.SearchDataType;
import com.mars.web.gov.search.inter.ParametersInterface;
import com.mars.web.gov.search.paramters.SearchParaInfo;

public class DrugParameterImpl implements ParametersInterface {
	private static Logger logger = LoggerFactory.getLogger(DrugParameterImpl.class.getName()) ;
	  
	protected Map<String,String[]> ParametersFromRequest = null;
	
	protected Hashtable<String,ArrayList<SearchParaInfo>> ParameterInfoList=new Hashtable<String,ArrayList<SearchParaInfo>>() ;
	
	public DrugParameterImpl()	{
		logger.logMethodBegin("DrugParameterImpl") ;
	}

	/**
	 * Get all parameters from request
	 * @param request HttpServletRequest contains a request from 
	 */
	@Override
	public void ParaseRequest(HttpServletRequest request) {
		logger.logMethodBegin("ParaseRequest") ;
		
		if (request==null) return ;
		ParametersFromRequest = request.getParameterMap() ;
		if (ParametersFromRequest==null) return ;
		
		logger.logInfo("ParaseRequest", 0, "Begin to Load parameters from Request");
		
		for (String key:ParameterInfoList.keySet()){
			/** initialize the value **/
			ArrayList<SearchParaInfo> currentList= ParameterInfoList.get(key) ;
			for (SearchParaInfo objPara:currentList)
			{
				objPara.setCurrentValue(null);
				String strKey = objPara.getSearchDisplayIndex() ;
				String[] value = ParametersFromRequest.getOrDefault(strKey, null) ;
				if (value==null){
					continue ;
				}
				if (value.length==0) continue ;
				logger.logInfo("ParaseRequest", 0, String.format("Find data from URL :[%s]", value[0]));
				objPara.setCurrentValue(value[0]);
			}
		}
		logger.logMethodEnd("ParaseRequest");
	}

	/**
	 * Ignored, as all data submitted from web side are formatted with right type.
	 */
	@Override
	public boolean CheckParameters() {
		logger.logMethodBegin("CheckParameters");
		
		logger.logMethodEnd("CheckParameters");
		return true;
	}

	/**
	 * Initialize parameter information.
	 * Those information could load from properties file.
	 * For demo, just harding code here.
	 * @author Marquis
	 */
	@Override
	public void InitParameterInfo() {
		ParameterInfoList.clear();
		PushAParameterInfoIntoParaHash(new SearchParaInfo("receivedate",SearchDataType.ST_Date,"receivedate[From]")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("receivedate",SearchDataType.ST_Date,"receivedate[To]")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("drug.drugstartdate",SearchDataType.ST_Date,"drug.drugstartdate")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("drug.drugenddate",SearchDataType.ST_Date,"drug.drugenddate")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("drug.medicinalproduct",SearchDataType.ST_String,"drug.medicinalproduct")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("patient.drug.drugdosagetext",SearchDataType.ST_Date,"patient.drug.drugdosagetext")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("patient.patientonsetage",SearchDataType.ST_Number,"patient.patientonsetage")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("patient.patientonsetageunit",SearchDataType.ST_Number,"patient.patientonsetageunit")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("limit",SearchDataType.ST_Date,"limit",1,0)) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("skip",SearchDataType.ST_Date,"skip",1,0)) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("patient.openfda.brand_name",SearchDataType.ST_String,"patient.openfda.brand_name")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("patient.openfda.product_type",SearchDataType.ST_String,"patient.openfda.product_type")) ;
		PushAParameterInfoIntoParaHash(new SearchParaInfo("count",SearchDataType.ST_String,"Index",2,0)) ;
		
	}
	
	/**
	 * Push Search Parameters into hashtable.
	 * <b>Notice (IMPORTANT):
	 * 	<p>
	 *    receivedate[From], receivedate[To] will be stored into Arraylist first, and then save to the same buck with key receivedate.
	 *  </p>
	 * </b> 
	 * @param objSearchPara Data to be pushed
	 */
	private void PushAParameterInfoIntoParaHash(SearchParaInfo objSearchPara){
		if (objSearchPara==null) return ;
		ArrayList<SearchParaInfo> lstCurrentPara=null ;
		String strCurrentKey ;
		if (!ParameterInfoList.containsKey(strCurrentKey=objSearchPara.getSearchIndexForFDA())){
			ParameterInfoList.put(strCurrentKey, lstCurrentPara=new ArrayList<SearchParaInfo>() ) ;
		}else{
			lstCurrentPara = ParameterInfoList.get(strCurrentKey) ;
		}
		lstCurrentPara.add(objSearchPara) ;
	}

	/**
	 * Navigate the ParameterInfoList, and build a URL.
	 * @return An URL which is available for FDA web side
	 */
	@Override
	public String BuildSearchURL() {
		logger.logMethodBegin("BuildSearchURL") ;
		String strResult="search=", strBusiness="", strFunction="";
		
		System.out.println(System.getProperty("properties.location")) ;
		
		for (String key:ParameterInfoList.keySet()){
			int iSearchType = (ParameterInfoList.get(key)).get(0).getSearchFunctionalityType() ;
			
			String strCurrentURL = BuildUrlByNode(ParameterInfoList.get(key), iSearchType) ; 
			if (strCurrentURL==null) continue ;
			
			switch(iSearchType){
			/** Business index **/
			case 0:
				if (strBusiness=="")
					strBusiness = strCurrentURL ;
				else{
					strBusiness = String.format("%s+AND+%s", strBusiness, strCurrentURL) ;
				}
				break ;
			/** functionality part, like limit and count**/
			case 2:
			case 1:
				if (strFunction=="")
					strFunction = strCurrentURL ;
				else{
					strFunction = String.format("%s&%s", strFunction, strCurrentURL) ;
				}
				break ;
			default:
				break ;
			}
			
		}
		strResult = String.format("search=%s&%s", strBusiness, strFunction) ;
		logger.logInfo("BuildSearchURL", 0, strResult);
		return strResult;
	}
	
	/**
	 * Build a string based on one node of the hashtable. 
	 * @param lstSearchInfo one Node information
	 * @return null,when setting errors. Searching-able information for FDA APIs.
	 */
	protected String BuildUrlByNode(ArrayList<SearchParaInfo> lstSearchInfo, int iSearchType){
		if (lstSearchInfo==null) return null ;
		if (lstSearchInfo.size()==0) return null ;
		if (lstSearchInfo.size()==1){
			if (lstSearchInfo.get(0).getCurrentValue()==null) return null ;
			if (iSearchType==0){
				String strResult = String.format("%s:\"%s\"", lstSearchInfo.get(0).getSearchIndexForFDA(),lstSearchInfo.get(0).getCurrentValue()) ;
				logger.logInfo("BuildUrlByNode", 0, String.format("Build One Node:[%s]", strResult));
				return strResult ;
			}else{
				if (iSearchType==2){
					String strResult = String.format("count=%s", lstSearchInfo.get(0).getCurrentValue()) ;
					logger.logInfo("BuildUrlByNode", 0, String.format("Build One Node:[%s]", strResult));
					return strResult ;
				}
				if (iSearchType==1){
					String strResult = String.format("limit=%s", lstSearchInfo.get(0).getCurrentValue()) ;
					logger.logInfo("BuildUrlByNode", 0, String.format("Build One Node:[%s]", strResult));
					return strResult ;
				}
			}
			
		}else{
			boolean isFirstValue=false ;
			String strFirstValue=null,strSecondValue=null ;
			if (lstSearchInfo.size()==2){
				for (int i=0;i<lstSearchInfo.size();i++)
				{
					isFirstValue=lstSearchInfo.get(i).getSearchDisplayIndex().indexOf("[From]")>0 ;
						
					if (isFirstValue){
						if (lstSearchInfo.get(i).getCurrentValue()!=null){
							strFirstValue = (String)lstSearchInfo.get(i).getCurrentValue();
						}
					}else{
						if (lstSearchInfo.get(i).getCurrentValue()!=null)
							strSecondValue= (String)lstSearchInfo.get(i).getCurrentValue() ;
					}
				}
				if ((strFirstValue==null)&&(strSecondValue==null)) {
					logger.logError("BuildUrlByNode", -1,String.format("parameters about %s setting are wrong. at least One of value is NULL. \r\n\tvalue1:[%s],value2[%s]",
							lstSearchInfo.get(0).getSearchIndexForFDA(), lstSearchInfo.get(0).getCurrentValue()==null?"null":lstSearchInfo.get(0).getCurrentValue(),
									lstSearchInfo.get(1).getCurrentValue()==null?"null":lstSearchInfo.get(1).getCurrentValue()));
					return null ;
				}else{					
					if (strFirstValue==null){						
						String strResult = String.format("%s:\"%s\"", lstSearchInfo.get(0).getSearchIndexForFDA(),strSecondValue) ;
						logger.logInfo("BuildUrlByNode", 0, String.format("Build One Node:[%s]", strResult));
						return strResult ;
					}else{
						if (strSecondValue==null){
							return String.format("%s:\"%s\"", lstSearchInfo.get(0).getSearchIndexForFDA(),strFirstValue) ;
						}else{
							return String.format("%s:[%s+TO+%s]", lstSearchInfo.get(0).getSearchIndexForFDA(),strFirstValue,strSecondValue) ;
						}
					}
				}
				
			}
		}
		
		return null ;
	}	
}

