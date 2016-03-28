package module.rest.hello;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.TestData;

public class HelloGetRestTest extends RestTestBase {
	
	public HelloGetRestTest() {
		super("/hello");
	}
	
    /* TEST CASES */
	
    @Test
    public void testV_PlainText() {
        String responseMsg = prepare("", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).accept(MediaType.TEXT_PLAIN).get(String.class);
        TestCase.assertEquals("Hello World", responseMsg);
    }
}
