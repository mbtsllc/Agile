package com.mars.web.gov.search.paramters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import com.mars.fda.FDASearchTypeEnums;
import com.mars.util.CommonConstants;
import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.search.inter.ParametersInterface;

public class ParamtersFactory {
	private static Logger logger = LoggerFactory.getLogger(ParamtersFactory.class.getName()) ;
	
	/**
	 * All "search" URLs returned from Web client contain "Type". Its value must be one of Drug, Food or Device from FDASearchTypeEnums.
	 * Parameters information via from FDASearchTypeEnums. A class is assigned to each Type.
	 * To make system flexible, the demo shows how to expand system easily in the future. Making information configurable.
	 * Information is configured in a property file. 
	 *
	 *	 * 
	 * <p><b>Steps:</b></p>
	 *   1, Get the parameter type of FDASearchTypeEnums
	 *   2, Get the class name from the properties file
	 *   3, Using reflection generates object instance and return.
	 *   
	 * @param strType
	 * @return An instance to deal with parameters from Web client
	 */
	public static ParametersInterface GetParametersInstanceByType(String strType){
		logger.logMethodBegin("GetParametersInstanceByType") ;
		
		/**1, Get the parameter type of FDASearchTypeEnums **/
		FDASearchTypeEnums enumParaType = FDASearchTypeEnums.GetSearchTypeByValue(strType) ;
		
		/** 2, Get the class name from the properties file **/
		String strClassName = GetConfigedClassNameByParameterType(enumParaType) ;
		
		/** 3, Using reflection generates object instance and return. **/
		ParametersInterface objInstance = GetObjectInstanceByName(strClassName) ;
		logger.logMethodEnd("GetParametersInstanceByType");
		return objInstance ;
	}

	/**
	 * Using reflection to create an instance of target class
	 * @param strClassName
	 * @return Object instance of Null
	 */
	private static ParametersInterface GetObjectInstanceByName(
			String strClassName) {
		logger.logMethodBegin("GetObjectInstanceByName") ;
		try{
			Class<?> objC = Class.forName(strClassName);
			Constructor<?>[] arrCM = objC.getConstructors() ;
			if (arrCM.length<=0) return null ;
			Constructor<?> objDefault=null ;
			for (int i=0 ;i<arrCM.length ;i++){
				if(arrCM[i].getGenericExceptionTypes().length==0){
					objDefault = arrCM[i] ;
					break ;
				}
			}
			if (objDefault==null) return null ;
			
			objDefault.setAccessible(true);
			Object objTarget = objDefault.newInstance() ;
			if (objTarget==null) {
				logger.logError("GetObjectInstanceByName", -1, "NewInstance returns NULL");
				return null ;
			}
			
			if (!(objTarget instanceof ParametersInterface)){
				logger.logError("GetObjectInstanceByName", -1, String.format("Class should impelement interface:%s", ParametersInterface.class.getTypeName()));
				return null ;
			}
			return (ParametersInterface)objTarget ;
		}catch(Exception e){
			e.printStackTrace(); 
			logger.logError("GetObjectInstanceByName", -1, String.format("Can't create instance [%s] via reflection, exceptions:%s", strClassName,e.getMessage()),e);
			return null;
		}finally
		{
			logger.logMethodEnd("GetObjectInstanceByName");
		}
				
	}

	/**
	 * Get configured class name from configuration file.
	 * <B>Steps:</B>
	 *   1, Get ConfigFileName from System constant class
	 *   2, Get key to browser.
	 *   3, Return the class name
	 *   	 
	 * @param enumParaType
	 * @return class name with package name, default is DrugParameterImpl.
	 */
	private static String GetConfigedClassNameByParameterType(
			FDASearchTypeEnums enumParaType) {
		logger.logMethodBegin("GetConfigedClassNameByParameterType");
		
		/** 1, Get ConfigFileName from System constant class **/
		String strConfigFileName = CommonConstants.PARAMETER_CONFIGFILE.getValue() ;
		 
		/** 2, Get key to browser **/
		String strKey = String.format("%s.%s", CommonConstants.PARAMETER_SEARCH_PREFIX.getValue(), strConfigFileName) ;
				
		/** 3, Return the class name **/
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("config.properties");
	 		// load a properties file
			prop.load(input); 
			return prop.getProperty(strKey, CommonConstants.DEFAULT_SEARCH_CLASS.getValue()) ;
	 
		} catch (IOException ex) {
			logger.logError("GetConfigedClassNameByParameterType", -1, ex.getMessage(),ex);
			return CommonConstants.DEFAULT_SEARCH_CLASS.getValue() ;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.logMethodEnd("GetConfigedClassNameByParameterType") ;
		}
		
	}
}
