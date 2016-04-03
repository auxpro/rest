package module.rest.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ap.web.rest.entity.AuxiliaryBean;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.rest.servlet.auxiliaries.AuxiliariesServlet;
import org.junit.Test;

import junit.framework.TestCase;
import module.rest.RestTestBase;
import tools.AssertHelper;
import tools.TestData;

public class ServicesPostRestTest extends RestTestBase {

	public ServicesPostRestTest() {
		super(AuxiliariesServlet.PATH);
	}
	
	/* TEST CASES */
	
	/* Negative Testing */

	@Test
	public void testI_postAuxiliaries_sameName() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AuxiliaryBean bean = TestData.getNextAuxiliary();
		AuxiliaryBean bean2 = TestData.getNextAuxiliary();
		bean2.setName(bean.getName());
		prepare("", userAdmin.getName(), userAdmin.getPassword()).post(Entity.entity(MAPPER.writeValueAsString(bean), MediaType.APPLICATION_JSON));
		Response response = prepare("", userAdmin.getName(), userAdmin.getPassword()).post(Entity.entity(MAPPER.writeValueAsString(bean2), MediaType.APPLICATION_JSON));
		TestCase.assertEquals(412, response.getStatus());
		TestCase.assertTrue(response.hasEntity());
	}
	@Test
	public void testI_postAuxiliaries_sameEmail() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AuxiliaryBean bean = TestData.getNextAuxiliary();
		AuxiliaryBean bean2 = TestData.getNextAuxiliary();
		bean2.setEmail(bean.getEmail());
		prepare("", userAdmin.getName(), userAdmin.getPassword()).post(Entity.entity(MAPPER.writeValueAsString(bean), MediaType.APPLICATION_JSON));
		Response response = prepare("", userAdmin.getName(), userAdmin.getPassword()).post(Entity.entity(MAPPER.writeValueAsString(bean2), MediaType.APPLICATION_JSON));
		TestCase.assertEquals(412, response.getStatus());
		TestCase.assertTrue(response.hasEntity());
	}
	
	/* Positive Testing */
	
	@Test
	public void testV_postAuxiliaries_asAdmin() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AuxiliaryBean bean = TestData.getNextAuxiliary();
		AuxiliaryBean response = prepare("", userAdmin.getName(), userAdmin.getPassword()).post(Entity.entity(MAPPER.writeValueAsString(bean), MediaType.APPLICATION_JSON), AuxiliaryBean.class);
		AssertHelper.assertAuxiliary(bean, response);
	}
}
