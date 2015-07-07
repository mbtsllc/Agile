package com.mars.web.gov.search.result;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;

/**
 * Get data from Json object.
 * Or Create instance based on Json information
 * @author  tiger
 * @version Demo
 * @date    Jun 28, 2015
 */
public class DrugSearchResultOpenFda {
	private static Logger logger = LoggerFactory.getLogger(DrugSearchResultOpenFda.class.getName()) ;
	/**
	 * Mapping to Unii from the result
	 */
	private ArrayList<String> unii =null ;
	private ArrayList<String> spl_id = null ;
	private ArrayList<String> product_ndc =null ;
	private ArrayList<String> substance_name = null ;
	private ArrayList<String> rxcui = null ;
	private ArrayList<String> spl_set_id = null;
	private ArrayList<String> product_type = null;
	private ArrayList<String> pharm_class_cs = null;
	private ArrayList<String> manufacturer_name = null;
	private ArrayList<String> brand_name = null;
	private ArrayList<String> route = null;
	private ArrayList<String> package_ndc = null;
	private ArrayList<String> pharm_class_epc = null;
	private ArrayList<String> generic_name = null;
	private ArrayList<String> application_number = null;
	
	/**
	 * 
	 * @param objTarget
	 * @param strFieldIndex
	 * @param strValue
	 */		
	public static void AddDataFieldValue(DrugSearchResultOpenFda objTarget, String strFieldIndex, String strValue){
		logger.logMethodBegin("AddDataFieldValue");
		logger.logInfo("AddDataFieldValue", 0, String.format("Parameters:\r\n\tFieldIndex:[%s]\r\n\tValue[%s]", strFieldIndex, strValue));
		if (objTarget==null) return ;
		Field objField= null ;
		try {
			objField=objTarget.getClass().getDeclaredField(strFieldIndex) ;
			if (objField==null) return ;			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue GetField raise Exception:[%s]", e.getMessage()),e);
			return ;
		}
		Object objTargetFieldInstance = null ; 
		try {
			objTargetFieldInstance =objField.get(objTarget) ;			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-IllegalArgumentException:[%s]", e.getMessage()),e);
			return ;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-IllegalAccessException:[%s]", e.getMessage()),e);
			return ;
		}
		
		String strCurrentFieldType = objField.getType().getName();
		logger.logInfo("AddDataFieldValue", 0, String.format("Get type:[%s]", strCurrentFieldType));		
		try {
			Object list = null ;
			if (objTargetFieldInstance == null ){  
				objTargetFieldInstance = objField.getType().newInstance();
				objField.set(objTarget, objTargetFieldInstance);
			}
			list = objTargetFieldInstance ;
			Method add = List.class.getDeclaredMethod("add",Object.class);
			add.invoke(list, strValue) ;
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		} catch (SecurityException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.logError("AddDataFieldValue", -1, String.format("AddDataFieldValue objField.get(objTarget) raise Exception-[%s]:[%s]",e.getClass().getName(), e.getMessage()),e);
			return ;
		}
		
		
		
	}

	public ArrayList<String> getUnii() {
		return unii;
	}

	public void setUnii(ArrayList<String> unii) {
		this.unii = unii;
	}

	public ArrayList<String> getSpl_id() {
		return spl_id;
	}

	public void setSpl_id(ArrayList<String> spl_id) {
		this.spl_id = spl_id;
	}

	public ArrayList<String> getProduct_ndc() {
		return product_ndc;
	}

	public void setProduct_ndc(ArrayList<String> product_ndc) {
		this.product_ndc = product_ndc;
	}

	public ArrayList<String> getSubstance_name() {
		return substance_name;
	}

	public void setSubstance_name(ArrayList<String> substance_name) {
		this.substance_name = substance_name;
	}

	public ArrayList<String> getRxcui() {
		return rxcui;
	}

	public void setRxcui(ArrayList<String> rxcui) {
		this.rxcui = rxcui;
	}

	public ArrayList<String> getSpl_set_id() {
		return spl_set_id;
	}

	public void setSpl_set_id(ArrayList<String> spl_set_id) {
		this.spl_set_id = spl_set_id;
	}

	public ArrayList<String> getProduct_type() {
		return product_type;
	}

	public void setProduct_type(ArrayList<String> product_type) {
		this.product_type = product_type;
	}

	public ArrayList<String> getPharm_class_cs() {
		return pharm_class_cs;
	}

	public void setPharm_class_cs(ArrayList<String> pharm_class_cs) {
		this.pharm_class_cs = pharm_class_cs;
	}

	public ArrayList<String> getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(ArrayList<String> manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public ArrayList<String> getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(ArrayList<String> brand_name) {
		this.brand_name = brand_name;
	}

	public ArrayList<String> getRoute() {
		return route;
	}

	public void setRoute(ArrayList<String> route) {
		this.route = route;
	}

	public ArrayList<String> getPackage_ndc() {
		return package_ndc;
	}

	public void setPackage_ndc(ArrayList<String> package_ndc) {
		this.package_ndc = package_ndc;
	}

	public ArrayList<String> getPharm_class_epc() {
		return pharm_class_epc;
	}

	public void setPharm_class_epc(ArrayList<String> pharm_class_epc) {
		this.pharm_class_epc = pharm_class_epc;
	}

	public ArrayList<String> getGeneric_name() {
		return generic_name;
	}

	public void setGeneric_name(ArrayList<String> generic_name) {
		this.generic_name = generic_name;
	}

	public ArrayList<String> getApplication_number() {
		return application_number;
	}

	public void setApplication_number(ArrayList<String> application_number) {
		this.application_number = application_number;
	}
	
}
