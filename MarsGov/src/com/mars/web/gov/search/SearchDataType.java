package com.mars.web.gov.search;

public enum SearchDataType {
	ST_String("Text"),
	ST_Date("Date"),
	ST_Number("Number");
	private String Value ="" ;
	SearchDataType(String value){
		this.Value =value ;
	} 
	
	public static SearchDataType GetSearchDataTypeByString(String strValue){
		if (strValue==null) return SearchDataType.ST_String ;
		
		if ("Text".compareToIgnoreCase(strValue)==0) return SearchDataType.ST_String ;
		if ("Date".compareToIgnoreCase(strValue)==0) return SearchDataType.ST_Date ;
		if ("Number".compareToIgnoreCase(strValue)==0) return SearchDataType.ST_Number;
		
		return SearchDataType.ST_String ;
	}
}
