package module.rest.users;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.UserBean;
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
	public void testI_getUser_getUnknownUser() {
		Response rsp = prepare("/dummy", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get();
		TestCase.assertEquals(404, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testI_getUser_asUnknownUser() {
		Response rsp = prepare("/user1", "dummy", "dummy").get();
		TestCase.assertEquals(401, rsp.getStatus());
		TestCase.assertFalse(rsp.hasEntity());
	}
	@Test
	public void testI_getUser_invalidPassword() {
		Response rsp = prepare("/user1", TestData.USER_USER1.NAME, "dummy").get();
		TestCase.assertEquals(401, rsp.getStatus());
		TestCase.assertFalse(rsp.hasEntity());
	}

	/* Positive Testing */

	// users/{userId} GET
	@Test
	public void testV_getUserResponse() {
		Response rsp = prepare("/user1", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get();
		TestCase.assertEquals(200, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testV_getUserObject_AsAdmin() {
		UserBean user = prepare("/user1", TestData.USER_ADMIN.NAME, TestData.USER_ADMIN.PASSWORD).get(UserBean.class);
		AssertHelper.assertUser(TestData.USER_USER1.BEAN, user);
	}
	@Test
	public void testV_getUserObject_AsSelf() {
		UserBean user = prepare("/user1", TestData.USER_USER1.NAME, TestData.USER_USER1.PASSWORD).get(UserBean.class);
		AssertHelper.assertUser(TestData.USER_USER1.BEAN, user);
	}
	@Test
	public void testV_getUserObject_AsOther() {
		UserBean user = prepare("/user1", TestData.USER_USER2.NAME, TestData.USER_USER2.PASSWORD).get(UserBean.class);
		AssertHelper.assertUser(TestData.USER_USER1.BEAN, user);
	}
}
