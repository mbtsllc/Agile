package com.mars.web.gov.search;

import java.util.HashMap;

import com.mars.web.gov.search.inter.DataDealAdapterInterface;

public class DataDealAdapterReactionOutCome implements DataDealAdapterInterface {
	private static HashMap<String, String> DataDisplayDataSet=new HashMap<String,String>() ;
	
	static{
		
		if (DataDisplayDataSet==null) DataDisplayDataSet =new HashMap<String,String>() ; ;
		DataDisplayDataSet.put("default", "unknow") ;
		
		DataDisplayDataSet.put("1", "Recovered/resolved") ;
		DataDisplayDataSet.put("2", "Recovering/resolving") ;
		DataDisplayDataSet.put("3", "Not recovered/not resolved") ;
		DataDisplayDataSet.put("4", "Recovered/resolved with sequelae") ;
		DataDisplayDataSet.put("5", "Fatal") ;
		DataDisplayDataSet.put("6", "Unknown") ;
	}
	
	@Override
	public String GetDisplayCaptionByValue(String strValue) {
		String strResult = DataDisplayDataSet.getOrDefault(strValue, null) ;
		if (strResult==null) return DataDisplayDataSet.getOrDefault("default", "") ;
		return strResult ;
	}

	@Override
	public String GetTitleForColumn(String strColumnNameInner) {
		// TODO Auto-generated method stub
		return "Type";
	}

	@Override
	public String GetWidthForColumn(String strColumnNameInner) {
		// TODO Auto-generated method stub
		return "180px";
	}

}
