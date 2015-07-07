package com.mars.web.gov.search;

import java.util.HashMap;

import com.mars.web.gov.search.inter.DataDealAdapterInterface;

public class DataDealAdapterSerious implements DataDealAdapterInterface {
	private static HashMap<String, String> DataDisplayDataSet=new HashMap<String,String>() ;
	
	static{
		if (DataDisplayDataSet==null) DataDisplayDataSet =new HashMap<String,String>() ; ;
		DataDisplayDataSet.put("default", "unknow") ;
		
		DataDisplayDataSet.put("1", "death|threatening|...") ;
		DataDisplayDataSet.put("2", "not Serious") ; 
	}
	@Override
	public String GetDisplayCaptionByValue(String strValue) {
		String strResult = DataDisplayDataSet.getOrDefault(strValue, null) ;
		if (strResult==null) return DataDisplayDataSet.getOrDefault("default", "") ;
		return strResult ;
	}
	
	@Override
	public String GetTitleForColumn(String strColumnName) {
		return "Serious";
	}  

	@Override
	public String GetWidthForColumn(String strColumnNameInner) {
		return "120";
	}

}
