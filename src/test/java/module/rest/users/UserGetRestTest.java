package module.rest.users;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.user.UserBean;
import org.ap.web.rest.servlet.users.UsersServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.AssertHelper;
import tools.TestData;

public class UserGetRestTest extends RestTestBase {

	public UserGetRestTest() {
		super(UsersServlet.PATH);
	}

	/* TEST CASES */

	/* Negative Testing */

	// users/{userId} GET
	@Test
	public void testI_getUser_getUnknownUser() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		Response rsp = prepare("/dummy", userAdmin.getName(), userAdmin.getPassword()).get();
		TestCase.assertEquals(404, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testI_getUser_asUnknownUser() throws Exception {
		Response rsp = prepare("/user1", "dummy", "dummy").get();
		TestCase.assertEquals(401, rsp.getStatus());
		TestCase.assertFalse(rsp.hasEntity());
	}
	@Test
	public void testI_getUser_invalidPassword() throws Exception {
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		Response rsp = prepare("/user1", userAux1.getName(), "dummy").get();
		TestCase.assertEquals(401, rsp.getStatus());
		TestCase.assertFalse(rsp.hasEntity());
	}

	/* Positive Testing */

	// users/{userId} GET
	@Test
	public void testV_getUserResponse() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		Response rsp = prepare("/user1", userAdmin.getName(), userAdmin.getPassword()).get();
		TestCase.assertEquals(200, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testV_getUserObject_AsAdmin() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		UserBean user = prepare("/user1", userAdmin.getName(), userAdmin.getPassword()).get(UserBean.class);
		AssertHelper.assertUser(userAux1, user);
	}
	@Test
	public void testV_getUserObject_AsSelf() throws Exception {
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		UserBean user = prepare("/user1", userAux1.getName(), userAux1.getPassword()).get(UserBean.class);
		AssertHelper.assertUser(userAux1, user);
	}
	@Test
	public void testV_getUserObject_AsOther() throws Exception {
		UserBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		UserBean userAux2 = TestData.getAuxiliaryFromJson("users_aux2.json");
		UserBean user = prepare("/user1", userAux2.getName(), userAux2.getPassword()).get(UserBean.class);
		// Informations are private
		userAux1.setName(null);
		userAux1.setPassword(null);
		userAux1.setEmail(null);
		userAux1.setActive(false);
		userAux1.setTutoSkipped(false);
		AssertHelper.assertUser(userAux1, user);
	}
}
