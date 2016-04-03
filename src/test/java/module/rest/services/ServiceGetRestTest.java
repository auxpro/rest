package module.rest.services;

import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.AuxiliaryBean;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.rest.servlet.services.ServicesServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.AssertHelper;
import tools.TestData;

public class ServiceGetRestTest extends RestTestBase {

	public ServiceGetRestTest() {
		super(ServicesServlet.PATH);
	}

	/* TEST CASES */

	/* Negative Testing */

	// users/{userId} GET
	@Test
	public void testI_getUnknownService() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		Response rsp = prepare("/dummy", userAdmin.getName(), userAdmin.getPassword()).get();
		TestCase.assertEquals(404, rsp.getStatus());
		TestCase.assertTrue(rsp.hasEntity());
	}
	@Test
	public void testI_asUnknownUser() throws Exception {
		Response rsp = prepare("/society1", "dummy", "dummy").get();
		TestCase.assertEquals(401, rsp.getStatus());
		TestCase.assertFalse(rsp.hasEntity());
	}
	@Test
	public void testI_invalidPassword() throws Exception {
		UserBean userSad1 = TestData.getServiceFromJson("users_sad1.json");
		Response rsp = prepare("/society1", userSad1.getName(), "dummy").get();
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
		userAux1.setRoles(null);
		AssertHelper.assertAuxiliary(userAux1, userAux);
	}
}
