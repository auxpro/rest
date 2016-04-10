package module.rest.auxiliaries;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.AuxiliaryBean;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.rest.servlet.auxiliaries.AuxiliariesServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.AssertHelper;
import tools.TestData;

public class AuxiliaryGetRestTest extends RestTestBase {

	public AuxiliaryGetRestTest() {
		super(AuxiliariesServlet.PATH);
	}

	/* TEST CASES */

	/* Negative Testing */

	// users/{userId} GET
	@Test
	public void testI_getUser_getUnknownAuxiliairy() throws Exception {
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
	public void testV_getAuxiliaryResponse() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		Response rsp = prepare("/user1", userAdmin.getName(), userAdmin.getPassword()).get();
		TestCase.assertEquals(200, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testV_getAuxiliaryObject_AsAdmin() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AuxiliaryBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		AuxiliaryBean userAux = prepare("/user1", userAdmin.getName(), userAdmin.getPassword()).get(AuxiliaryBean.class);
		AssertHelper.assertAuxiliary(userAux1, userAux);
	}
	@Test
	public void testV_getAuxiliaryObject_AsSelf() throws Exception {
		AuxiliaryBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		AuxiliaryBean userAux = prepare("/user1", userAux1.getName(), userAux1.getPassword()).get(AuxiliaryBean.class);
		AssertHelper.assertAuxiliary(userAux1, userAux);
	}
	@Test
	public void testV_getAuxiliaryObject_AsOther() throws Exception {
		AuxiliaryBean userAux1 = TestData.getAuxiliaryFromJson("users_aux1.json");
		AuxiliaryBean userAux2 = TestData.getAuxiliaryFromJson("users_aux2.json");
		AuxiliaryBean userAux = prepare("/user1", userAux2.getName(), userAux2.getPassword()).get(AuxiliaryBean.class);
		// Informations are private
		userAux1.setName(null);
		userAux1.setPassword(null);
		userAux1.setEmail(null);
		userAux1.setActive(false);
		userAux1.setTutoSkipped(false);
		AssertHelper.assertAuxiliary(userAux1, userAux);
	}
}
