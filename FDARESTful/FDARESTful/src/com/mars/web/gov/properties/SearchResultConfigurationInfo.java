package com.mars.web.gov.properties;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;

/**
 * Configurated format is : Type;visible;
 * @author  tiger
 * @version Demo
 * @date    Jun 24, 2015
 */
public class SearchResultConfigurationInfo {
	private static Logger logger=LoggerFactory.getLogger(SearchResultConfigurationInfo.class.getName()) ;
	
	private String ConfigurationKey ;
	private String Value ;
	
	private int Level;
	private String TypeName ;
	private String TypeAddition;
	private String Visible ;
	private String Caption ;
	private String FieldInfoJson ;
	
	private boolean isDataNode ;
	
	public SearchResultConfigurationInfo(String strKeyFromProperties,String strValueFromProperties,boolean isDataNode){
		String keyWithoutPrefix=removePrefix(strKeyFromProperties) ;
		this.setConfigurationKey(keyWithoutPrefix);
		this.setValue(strValueFromProperties);
		this.setDataNode(isDataNode);
		
		alystValue() ;
	}
	
	/** 
	 * format:
	 *  [Visible;Type;TypeAddition;Caption]
	 */
	private void alystValue() {
		String[] arrKeys=ConfigurationKey.split("\\.") ;
		Level = arrKeys.length ;
		
		String[] arrVs = Value.split(";");
		
		switch(arrVs.length){
		case 5:
			setFieldInfoJson(arrVs[4]) ;
		case 4:
			this.setCaption(arrVs[3]);
		case 3:
			setTypeAddition(arrVs[2]) ;
		case 2:
			TypeName = arrVs[1] ;
		default:
			Visible = arrVs[0] ;
			if (Visible==null){
				Visible = PropertiesConstants.CNST_PROPERTIES_VISIBLE ;
			}else{
				if (Visible=="")
					Visible = PropertiesConstants.CNST_PROPERTIES_VISIBLE ;
			}
		}
		if ((this.FieldInfoJson==null)||(this.FieldInfoJson=="")){
			this.FieldInfoJson=ConfigurationKey ;
		}
		logger.logInfo("alystValue", 0, String.format("AlystValue, TypeAddition:[%s], TypeName:[%s], Visible:[%s], Caption:[%s]", TypeAddition, TypeName, Visible,Caption));
	}
  
	private String removePrefix(String strKeyFromProperties){
		if (strKeyFromProperties.startsWith(PropertiesConstants.CNST_PROPERTIES_PREFIX)){
			return strKeyFromProperties.substring(PropertiesConstants.CNST_PROPERTIES_PREFIX.length()) ;
		}else return strKeyFromProperties ;
	}
	
	public String getLastPartOfKey(){
		if (ConfigurationKey==null) return null ;
		if ((FieldInfoJson!=null)&&(FieldInfoJson.compareToIgnoreCase(ConfigurationKey)!=0)&&(FieldInfoJson!="")){
			return FieldInfoJson ;
		}
		int iLast = ConfigurationKey.lastIndexOf(".") ;
		if (iLast<0) return ConfigurationKey ;
		return ConfigurationKey.substring(iLast+1) ;
	}
	
	public String getConfigurationKey() {
		return ConfigurationKey;
	}

	public void setConfigurationKey(String configurationKey) {
		ConfigurationKey = configurationKey;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public String getVisible() {
		return Visible;
	}

	public void setVisible(String visible) {
		Visible = visible;
	}

	public String getTypeAddition() {
		return TypeAddition;
	}

	public void setTypeAddition(String typeAddition) {
		TypeAddition = typeAddition;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}

	public boolean isDataNode() {
		return isDataNode;
	}

	public void setDataNode(boolean isDataNode) {
		this.isDataNode = isDataNode;
	}

	public String getFieldInfoJson() {
		return FieldInfoJson;
	}

	public void setFieldInfoJson(String fieldInfoJson) {
		FieldInfoJson = fieldInfoJson;
	}

	public boolean isSampleData() {
		if (TypeName==null) return false ;
		if (TypeName=="") return false ;
		if ("String".compareToIgnoreCase(TypeName)==0) return true ;
		if ("Date".compareToIgnoreCase(TypeName)==0) return true ;
		if ("int".compareToIgnoreCase(TypeName)==0) return true ;
		
		return false;
	}
}
