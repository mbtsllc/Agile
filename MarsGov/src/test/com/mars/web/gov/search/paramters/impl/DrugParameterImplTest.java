package test.com.mars.web.gov.search.paramters.impl;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mars.web.gov.search.paramters.impl.DrugParameterImpl;
import test.com.mars.web.gov.search.paramters.impl.TestHttpServletRequest;
  
public class DrugParameterImplTest {

	private static TestHttpServletRequest Request=new TestHttpServletRequest() ; 
	DrugParameterImpl objPara=new DrugParameterImpl() ;
	  
	@Before  
	public void setUp() throws Exception {

		Request.pushParameter("Limit", "20");
		Request.pushParameter("receivedate[To]", "20140822");
		Request.pushParameter("receivedate[From]", "20100822");
		Request.pushParameter("patient.drug.drugdosagetext", "TestValue");
		Request.pushParameter("Index", "patient.drug.drugdosagetext");
		  
		objPara.InitParameterInfo();
		objPara.ParaseRequest(Request);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown,clear()");
		Request.getParameterMap().clear(); 
	}

	@Test
	public void testDrugParameterImpl() {
		//fail("Not yet implemented");
	}

	@Test
	public void testParaseRequest() {
		//o
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckParameters() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testBuildSearchURL(){
		objPara.BuildSearchURL() ;
	}

}
