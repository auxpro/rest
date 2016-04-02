package module.service.users;

import org.ap.web.internal.APException;
import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.service.users.UsersMongoService;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import module.service.MongoServiceTestBase;
import tools.AssertHelper;
import tools.TestData;

public class UsersMongoServiceTest extends MongoServiceTestBase {

	/* TEST DATA */
	
	private UsersMongoService _svc;
	@Before
	public void setUpService() {
		_svc = new UsersMongoService(CONN);
	}
	
	/* TEST CASES */

	// Negative Testing //

	@Test(expected=APException.class)
	public void testI_CheckUserUnknown() throws Exception {
		new UsersMongoService().checkUser("dummy", "dummy");
	}
	@Test(expected=APException.class)
	public void testI_CheckUserInvalid() throws Exception {
		new UsersMongoService().checkUser("admin", "dummy");
	}
	@Test(expected=APException.class)
	public void testI_UpdateUserInvalid() throws Exception {
		_svc.updateUser(new Document("id", "dummy"));
	}
	@Test(expected=APException.class)
	public void testI_DeleteUserInvalid() throws Exception {
		_svc.deleteUser(new Document("id", "dummy"));
	}

	// Positive Testing //

	@Test
	public void testV_CheckUserValid() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AssertHelper.assertUser(userAdmin, BeanConverter.convertToUser(_svc.checkUser(userAdmin.getName(), userAdmin.getPassword())));
	}
	@Test
	public void testV_getUserByName_Valid() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AssertHelper.assertUser(userAdmin, BeanConverter.convertToUser(_svc.getUserByName(userAdmin.getName())));
	}
	@Test
	public void testV_getUserByName_Invalid() throws Exception {
		TestCase.assertNull(_svc.getUserByName("dummy"));
	}
	@Test
	public void testV_CheckEmailExists() throws Exception {
		UserBean userAdmin = TestData.getUserFromJson("users_admin.json");
		AssertHelper.assertUser(userAdmin, BeanConverter.convertToUser(_svc.getUserByEmail(userAdmin.getEmail())));
	}
	@Test
	public void testV_getUserByEmail_Invalid() throws Exception {
		TestCase.assertNull(_svc.getUserByEmail("dummy@lol.fr"));
	}
	@Test
	public void testV_CountUsers() throws APException {
		TestCase.assertEquals(5, _svc.getUsers().size());
	}
	@Test
	public void testV_CreateUser() throws Exception {
		int number = _svc.getUsers().size();
		UserBean user = new UserBean();
		user.setName("newuser");
		user.setName("newuser@newuser.com");
		user.setPassword("newuserpass");
		user.setActive(true);
		user.setRoles(new String[] {"adv"});
		UserBean created = BeanConverter.convertToUser(_svc.createUser(BeanConverter.convertToDocument(user)));
		AssertHelper.assertUser(user, created);
		TestCase.assertEquals(number + 1, _svc.getUsers().size());
	}
	@Test
	public void testV_UpdateUser() throws Exception {
		UserBean user = BeanConverter.convertToUser(_svc.getUserByName("user1"));
		user.setEmail("newemail@lol.fr");
		UserBean created = BeanConverter.convertToUser(_svc.updateUser(BeanConverter.convertToDocument(user)));
		AssertHelper.assertUser(user, created);
	}
	@Test
	public void testV_DeleteUser() throws Exception {
		UserBean user = BeanConverter.convertToUser(_svc.getUserByName("user1"));
		UserBean deleted = BeanConverter.convertToUser(_svc.deleteUser(BeanConverter.convertToDocument(user)));
		AssertHelper.assertUser(user, deleted);
	}
	
	/* TEST HELPERS */
	
	
	
}
