package com.mars.web.gov.search;

import java.util.HashMap;

import com.mars.web.gov.search.inter.DataDealAdapterInterface;

public class DataDealAdapterSetAgeUnit implements DataDealAdapterInterface {
	private static HashMap<String, String> DataDisplayDataSet=new HashMap<String,String>() ;
	
	static{
		if (DataDisplayDataSet==null) DataDisplayDataSet =new HashMap<String,String>() ; ;
		DataDisplayDataSet.put("default", "unknow") ;
		
		DataDisplayDataSet.put("800", "Decade") ;
		DataDisplayDataSet.put("801", "Year") ;
		DataDisplayDataSet.put("802", "Month") ;
		DataDisplayDataSet.put("803", "Week") ;
		DataDisplayDataSet.put("804", "Day") ;
		DataDisplayDataSet.put("805", "Hour") ;
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
		return "Set Age Unit";
	}

	@Override
	public String GetWidthForColumn(String strColumnNameInner) {
		// TODO Auto-generated method stub
		return "120px";
	}

}
