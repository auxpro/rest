package module.rest.auth;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.UserBean;
import org.ap.web.rest.servlet.auth.AuthServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.TestData;

public class AuthGetRestTest extends RestTestBase {

	public AuthGetRestTest() {
		super(AuthServlet.PATH);
	}
	
	/* TEST CASES */
	
	/* Negative Testing */
	
	@Test
	public void testI_getAuth_UnknownUser() {
		Response response = prepare("", "dummy", "dummy").get();
		TestCase.assertEquals(401, response.getStatus());
		TestCase.assertFalse(response.hasEntity());
	}
	
	/* Positive Testing */
	
	@Test
	public void testV_getAuth_AdminUser() {
		UserBean user = prepare("", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get(UserBean.class);
		TestCase.assertEquals("admin", user.getName());
		TestCase.assertEquals(1, user.getRoles().length);
		TestCase.assertNull(user.getPassword());
	}
	@Test
	public void testV_getAuth_User() {
		UserBean user = prepare("", TestData.USER_USER1.NAME, TestData.USER_USER1.PASSWORD).get(UserBean.class);
		TestCase.assertEquals("user1", user.getName());
		TestCase.assertEquals(1, user.getRoles().length);
		TestCase.assertNull(user.getPassword());
	}
}
