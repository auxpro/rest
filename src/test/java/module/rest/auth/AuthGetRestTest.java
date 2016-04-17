package module.rest.auth;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.constant.EUserType;
import org.ap.web.rest.entity.user.UserBean;
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
	public void testV_getAuth_AdminUser() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		UserBean user = prepare("", userAdmin.getName(), userAdmin.getPassword()).get(UserBean.class);
		TestCase.assertEquals("admin", user.getName());
		TestCase.assertEquals(EUserType.ADMIN.getId(), user.getType());
		TestCase.assertNull(user.getPassword());
	}
	@Test
	public void testV_getAuth_User() throws Exception {
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		UserBean user = prepare("", userAux1.getName(), userAux1.getPassword()).get(UserBean.class);
		TestCase.assertEquals("user1", user.getName());
		TestCase.assertEquals(EUserType.AUXILIARY.getId(), user.getType());
		TestCase.assertNull(user.getPassword());
	}
}
