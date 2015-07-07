package com.mars.web.gov.properties;

import com.mars.util.Logger;
import com.mars.util.LoggerFactory;
import com.mars.web.gov.search.inter.DataDealAdapterInterface;

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
	
	/**
	 * To increase performance, keep a instance of adapter when object is MarsObject
	 */
	private DataDealAdapterInterface AdapterInstance=null ;
	
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
	
	public boolean isMarsObject(){
		return TypeName==null?false:(TypeName==""?false:TypeName.compareToIgnoreCase("MarsObject")==0) ;
	}
	@Override
	public String toString(){
		return String.format("FieldInfoJson:[%s]\r\n\tType:[%s] TypeAddition:[%s] Visible:[%s] Caption:[%s]", this.FieldInfoJson,
				this.TypeName==null?"":this.TypeName, this.TypeAddition==null?"":this.TypeAddition, 
						this.Visible==null?"":this.Value,this.Caption==null?"":this.Caption) ;
				
	}
	
	public String ConvertToCaptionDataByValue(String strValue){
		if (!isMarsObject()) {
			logger.logInfo("ConvertToCaptionDataByValue", 0, String.format("The type [%s] configurated is not a \"MarsObject\" current:\r\n\t[%s]", this.TypeName,this.toString()));
			return strValue ;
		}
		
		/**
		 * Get object instance, and check whether it is instance of DataDealAdapterInterface
		 */
		try{
			Class<?> objC = Class.forName(TypeAddition) ;
			Object objInst = objC.newInstance() ;
			if (objInst instanceof DataDealAdapterInterface){
				return ((DataDealAdapterInterface) objInst).GetDisplayCaptionByValue(strValue) ;
			}
			return strValue ;
		}catch(Exception e){
			logger.logError("ConvertToCaptionDataByValue", -1, String.format("Can't use reflection to get data [%s], with Exception:[%s], \r\n\tClassName:[%s]",
					strValue,  e.getMessage(), this.TypeAddition));
			return strValue ;
		}
		
	}
	
	public String getFieldNameForColumn(){
		return getLastPartOfKey() ;
	}

	public String getTitleForColumn() {
		if (!isMarsObject()) return Caption==null?this.getLastPartOfKey():(Caption==""?this.getLastPartOfKey():Caption) ;
		try{
			if (AdapterInstance==null){		
				InitAdapterInstance() ;				
			}			
			return AdapterInstance.GetTitleForColumn(this.ConfigurationKey) ;
		}catch(Exception e){
			logger.logError("getTitleForColumn", -1, String.format("Exceptions:[%s]----Can't use reflection to get TitleforColumn[%s] ", e.getMessage(), this.toString()), e);
			return this.getLastPartOfKey() ;
		}
		
	}
	
	private void InitAdapterInstance(){
		try{
			if (AdapterInstance==null){		
				Object objInst = null ;
				Class<?> objC = Class.forName(TypeAddition) ;
				objInst = objC.newInstance() ;
				if (objInst instanceof DataDealAdapterInterface){
					AdapterInstance = (DataDealAdapterInterface)objInst ;
				}
				return  ;
			}
			return ;
		}catch(Exception e){
			logger.logError("getTitleForColumn", -1, String.format("Exceptions:[%s]----Can't use reflection to get TitleforColumn[%s] ", e.getMessage(), this.toString()), e);
			return ;
		}
	}

	public String getColumnWidth() {
		//default value is auto
		if (!isMarsObject()) return "100" ;
		if (AdapterInstance==null)
			InitAdapterInstance() ;
		if (AdapterInstance==null) return "100" ;
		return AdapterInstance.GetWidthForColumn(this.ConfigurationKey) ;
	}

	
	
}
