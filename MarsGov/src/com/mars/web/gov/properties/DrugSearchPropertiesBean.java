package com.mars.web.gov.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.search.GovSearchConstants;

public class DrugSearchPropertiesBean {
	private static Logger logger = LoggerFactory.getLogger(DrugSearchPropertiesBean.class.getName()) ;
	
	private static DrugSearchPropertiesBean gobjDrugSearchBean = null ;
	
	Properties mobjProp = new Properties();
	
	private HashMap<Integer, HashMap<String, SearchResultConfigurationInfo>> ResultConfigurationInfo=new HashMap<Integer, HashMap<String, SearchResultConfigurationInfo>>() ;
	private static boolean isDataInitialized = false;
	public static DrugSearchPropertiesBean getInstance()
	{
		logger.logMethodBegin("DrugSearchPropertiesBean");
		if (gobjDrugSearchBean==null){
			/** Load properties file **/
			gobjDrugSearchBean = new DrugSearchPropertiesBean() ;
			gobjDrugSearchBean.LoadPropertiesFile() ;
		}
		return gobjDrugSearchBean ;
	}
	
	/**
	 * Load File into memory
	 */
	private void LoadPropertiesFile() {
		logger.logMethodBegin("LoadPropertiesFile");
		InputStream input = null;
		 
		try {
			//input = new FileInputStream(System.getProperty("properties.location")+"\\"+"DrugSearchProperties.properties");
			input = new FileInputStream("/tmp/mars/"+"DrugSearchProperties.properties");
	 		// load a properties file
			mobjProp.load(input); 			
			loadAllConfigurationInfo();
		}catch(Exception e){
			e.printStackTrace(); 
			logger.logError("LoadPropertiesFile", -1, String.format("Can't load Properties file:[%s] with Exception:[%s]", GovSearchConstants.CNST_DRUGSEARCH_FILENAME,e.getMessage()),e);
			mobjProp = null ;
		}finally{
			if (input!=null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.logError("LoadPropertiesFile", -1, "Exception:"+e.getMessage(),e);
				}
			logger.logMethodEnd("LoadPropertiesFile");	
		}
	}

	private void loadAllConfigurationInfo() {
		logger.logMethodBegin("loadAllConfigurationInfo");
		if (isDataInitialized) return ;
		String strDataNodePrefix=mobjProp.getProperty(PropertiesConstants.CNST_PROPERTIES_DATA_KEY) ;
		strDataNodePrefix="Json.results" ;
		for (Object oneKey: mobjProp.keySet()){
			String strOneKey = (String)oneKey ;
			if (strOneKey=="") continue ;
			if (strOneKey.startsWith(PropertiesConstants.CNST_PROPERTIES_PREFIX)){
				String value=mobjProp.getProperty(strOneKey, null) ;
				if (value==null) continue ;
				SearchResultConfigurationInfo objconfig =new SearchResultConfigurationInfo(strOneKey,value,strOneKey.startsWith(strDataNodePrefix));
				int iLevel = objconfig.getLevel() ;
				HashMap<String, SearchResultConfigurationInfo> objLevelHash=getResultConfigurationInfo().get(Integer.valueOf(iLevel)) ;
				if (objLevelHash==null){
					objLevelHash = new HashMap<String, SearchResultConfigurationInfo>(); 
					getResultConfigurationInfo().put(Integer.valueOf(iLevel), objLevelHash) ;
				}
				String strConfigureKeyWithoutPrefix = objconfig.getConfigurationKey(); 
				if (!objLevelHash.containsKey(strConfigureKeyWithoutPrefix)){
					objLevelHash.put(strConfigureKeyWithoutPrefix,objconfig) ;
				}
				continue ;
			}			
		}
		isDataInitialized = true ;
		logger.logMethodEnd("loadAllConfigurationInfo");
	}  
  
	/**
	 * Get URL pattern prefix information
	 * @return
	 */
	public String GetFDASearchPattern(){
		if (mobjProp==null){
			logger.logError("GetFDASearchPattern", -1, "No properties instance is load");
			return null ;
		}
		String strResult = mobjProp.getProperty(GovSearchConstants.CNST_SEARCH_DRUG_PATTERN_PREFIX, null) ;
		logger.logInfo("GetFDASearchPattern", 0, String.format("Get parttern URL for FDA Search is:[%s]",strResult));
		return strResult ;
	}

	public HashMap<Integer, HashMap<String, SearchResultConfigurationInfo>> getResultConfigurationInfo() {
		return ResultConfigurationInfo;
	}

	public void setResultConfigurationInfo(
			HashMap<Integer, HashMap<String, SearchResultConfigurationInfo>> resultConfigurationInfo) {
		ResultConfigurationInfo = resultConfigurationInfo;
	}
	
	public HashMap<String, SearchResultConfigurationInfo> getConfigurationInfoByLevel(int iLevel){
		if (ResultConfigurationInfo==null) return null ;
		
		return ResultConfigurationInfo.getOrDefault(Integer.valueOf(iLevel),null) ;
	}

	public HashMap<String, SearchResultConfigurationInfo> getConfigurationInfoByLevel(
			int iLevel, String field) {
		HashMap<String, SearchResultConfigurationInfo> hsLevelAll = ResultConfigurationInfo.getOrDefault(Integer.valueOf(iLevel),null) ;
		if (hsLevelAll==null) return null ;
		HashMap<String, SearchResultConfigurationInfo> hsResult = new HashMap<String, SearchResultConfigurationInfo>() ;
		for (String key:hsLevelAll.keySet()){
			if (key.indexOf(String.format("%s.", field))>=0){
				hsResult.put(key, hsLevelAll.get(key)) ;
			}
		}
		return hsResult;
	}
}
