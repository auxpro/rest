package module.rest.services;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.UserBean;
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
	public void testI_getUsers_NotAllowed() throws Exception {
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		Response response = prepare("", userAux1.getName(), userAux1.getPassword()).get();
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
	public void testV_getUsers() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		String responseMsg = prepare("", userAdmin.getName(), userAdmin.getPassword()).get(String.class);
		TestCase.assertTrue(responseMsg.contains("\"name\":\"user1\""));
		TestCase.assertTrue(responseMsg.contains("\"name\":\"admin\""));
		TestCase.assertFalse(responseMsg.contains("\"password\""));
	}
	@Test
	public void testV_getUsers_response() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		Response rsp = prepare("", userAdmin.getName(), userAdmin.getPassword()).get();
		TestCase.assertEquals(200, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
}
