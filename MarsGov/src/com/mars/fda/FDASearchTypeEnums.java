package com.mars.fda;

/**
 * SeachType of Enums. 
 * Based on the requirement, three types of FDA search engine should 
 * be applied including Drugs, Devices and food
 * @author  tiger
 * @version Demo
 * @date    Jun 20, 2015
 */
public enum FDASearchTypeEnums {
	 
	SE_Drug("Drug"),
	SE_Device("Device"),
	SE_Food("Food") ;
	
	private String value = "" ;
	
	public void SetValue(String strValue)
	{
		this.value = strValue ;
	}
	FDASearchTypeEnums(String strValue)
	{
		SetValue(strValue) ;
	}
	public String GetValue()
	{
		return this.value ;
	}
	
	/**
	 * To get special parameter type. 
	 * synchronized is required to make thread safety.
	 * @param strValue
	 * @return Drug, Device or Food. 
	 */
	public synchronized static FDASearchTypeEnums GetSearchTypeByValue(String strValue){
		/** default value **/
		if (strValue == null) return SE_Drug ; 
		if (SE_Drug.GetValue().compareToIgnoreCase(strValue)==0){
			return SE_Drug ;
		}
		if (SE_Device.GetValue().compareToIgnoreCase(strValue)==0){
			return SE_Device ;
		}
		if (SE_Food.GetValue().compareToIgnoreCase(strValue)==0){
			return SE_Food ;
		}
		return SE_Drug ;
	}
}
