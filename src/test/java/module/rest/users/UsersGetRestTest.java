package module.rest.users;

import javax.ws.rs.core.Response;

import org.ap.web.rest.servlet.users.UsersServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.TestData;

public class UsersGetRestTest extends RestTestBase {

	public UsersGetRestTest() {
		super(UsersServlet.PATH);
	}
	
	/* TEST CASES */
	
	/* Negative Testing */
	
	@Test
	public void testI_getUsers_NotAllowed() {
		Response response = prepare("", TestData.USER_USER1.NAME, TestData.USER_USER1.PASSWORD).get();
		TestCase.assertEquals(403, response.getStatus());
		TestCase.assertFalse(response.hasEntity());
	}
	@Test
	public void testI_getUsers_UnknownUser() {
		Response response = prepare("", "dummy", "dummy").get();
		TestCase.assertEquals(401, response.getStatus());
		TestCase.assertFalse(response.hasEntity());
	}
	
	/* Positive Testing */
	
	@Test
	public void testV_getUsers() {
		String responseMsg = prepare("", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get(String.class);
		TestCase.assertTrue(responseMsg.contains("\"name\":\"user1\""));
		TestCase.assertTrue(responseMsg.contains("\"name\":\"admin\""));
		TestCase.assertFalse(responseMsg.contains("\"password\""));
	}
	@Test
	public void testV_getUsers_response() {
		Response rsp = prepare("", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get();
		TestCase.assertEquals(200, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
}
