package test.com.mars.web.gov.demo.drug;

import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.json.JSONArray;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mars.web.gov.demo.drug.SearchAction;
import com.mars.web.gov.properties.SearchResultConfigurationInfo;

public class SearchActionTest {
	SearchAction objSearchAction = new SearchAction() ;
	String strURL ="" ;
	@Before
	public void setUp() throws Exception {
		strURL ="https://api.fda.gov/drug/event.json?search=receivedate:[20040101+TO+20081231]&limit=3";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDataFromFDAServer() {
		objSearchAction.GetDataFromFDAServer(strURL) ;
		
	}
	
	@Test
	public void testGenerateColumnInfoBasedLevel(){
		ArrayList<SearchResultConfigurationInfo> lstConfigObj = new ArrayList<SearchResultConfigurationInfo>() ;
		//String strKeyFromProperties,String strValueFromProperties,boolean isDataNode
		SearchResultConfigurationInfo obj1=new SearchResultConfigurationInfo("Json.results.patient.serious","Visible;MarsObject;com.mars.web.gov.search.DataDealAdapterSerious;Serious", true) ;
		SearchResultConfigurationInfo obj2=new SearchResultConfigurationInfo("Json.results.patient.transmissiondate", "Visible;Date;YYYYMMdd;Transmission Date",true) ;
		lstConfigObj.add(obj1) ;
		lstConfigObj.add(obj2) ;
		
		JSONArray objArray = objSearchAction.GenerateColumnInfoBasedLevel(lstConfigObj) ;
		System.out.println(objArray.toString()) ;

	}

}
