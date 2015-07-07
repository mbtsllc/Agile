package test.com.mars.web.gov.action;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mars.web.gov.action.HttpReader;

public class HttpReaderTest {

	private static HttpReader objReader = new HttpReader() ;
	private String TestURL = "https://api.fda.gov/drug/event.json?search=patient.drug.openfda.pharm_class_epc:\"nonsteroidal+anti-inflammatory+drug\"&count=patient.reaction.reactionmeddrapt.exact" ;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetURLInfoByHttp() {
		try {
			String strResult = objReader.getURLInfoByHttp(TestURL) ;
			System.out.println(strResult);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
