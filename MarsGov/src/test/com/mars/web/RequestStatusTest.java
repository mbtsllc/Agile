package test.com.mars.web;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mars.util.CommonConstants;
import com.mars.web.RequestStatus;

public class RequestStatusTest {
	protected static RequestStatus requestStatus=new RequestStatus() ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("RequestStatusTest setUpBeforeClass Begin...");
		requestStatus.setCurrentStatus(CommonConstants.WEB_SUCCESS.getValue()) ;
		requestStatus.setInformationToDisplay("Test Information");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("RequestStatusTest tearDownAfterClass end");
	}
	
	@Test
	public void testGenerateJSON() {
		System.out.println(String.format("Result Is: %s",requestStatus.generateJSON()));
	}

}
