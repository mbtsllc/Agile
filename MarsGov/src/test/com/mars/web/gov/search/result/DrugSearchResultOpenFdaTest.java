package test.com.mars.web.gov.search.result;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mars.web.gov.search.result.DrugSearchResultOpenFda;

public class DrugSearchResultOpenFdaTest {
	static DrugSearchResultOpenFda objTestTarget = null ;
	@Before
	public void setUp() throws Exception {
		objTestTarget = new DrugSearchResultOpenFda() ;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDataFieldValue() {
		DrugSearchResultOpenFda.AddDataFieldValue(objTestTarget, "spl_id", "TestValue 1");
		DrugSearchResultOpenFda.AddDataFieldValue(objTestTarget, "spl_id", "TestValue 2");
		System.out.println(objTestTarget.getSpl_id()) ;
	}

}
