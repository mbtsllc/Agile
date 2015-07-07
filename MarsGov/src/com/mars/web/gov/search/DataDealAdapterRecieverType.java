package com.mars.web.gov.search;

import java.util.HashMap;

import com.mars.web.gov.search.inter.DataDealAdapterInterface;

public class DataDealAdapterRecieverType implements DataDealAdapterInterface {
	private static HashMap<String, String> DataDisplayDataSet=new HashMap<String,String>() ;
	
	static{
		if (DataDisplayDataSet==null) DataDisplayDataSet =new HashMap<String,String>() ; ;
		DataDisplayDataSet.put("default", "unknow") ;
		
		DataDisplayDataSet.put("1", "Pharmaceutical Company") ;
		DataDisplayDataSet.put("2", "Regulatory Authority") ;
		DataDisplayDataSet.put("3", "Health Professional") ;
		DataDisplayDataSet.put("4", "Regional Pharmacovigilance Center") ;
		DataDisplayDataSet.put("5", "WHO Collaborating Center for International Drug Monitoring") ;
		DataDisplayDataSet.put("6", "Other") ;
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
