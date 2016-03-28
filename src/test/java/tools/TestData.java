package tools;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.service.MongoConnection;
import org.ap.web.service.MongoConstants;
import org.bson.Document;
public class TestData {

	// Db information
	public static final String DB_SERVER = "localhost";
	public static final int DB_PORT   = 27017;
	public static final String DB_NAME   = "test-db";
	public static final String DB_USER   = "";
	public static final String DB_PASS   = "";

	// Host information
	public static final String HOST_PROTOCOL = "http";
	public static final String HOST_SERVER   = "localhost";
	public static final String HOST_PORT     = "8090";
	public static final String HOST_URLBASE  = "apweb/rest";

	public static final String BASE_URL = HOST_PROTOCOL + "://" + HOST_SERVER + ":" + HOST_PORT + "/" + HOST_URLBASE;
	public static final URI BASE_URI = UriBuilder.fromUri(TestData.BASE_URL).build();

	// Users
	public static class USER_ADMIN {
		public static final UserBean BEAN = new UserBean();
		public static final String NAME = "admin";
		public static final String PASSWORD = "secret";
		public static final String EMAIL = "admin@admin.com";
		public static final boolean ACTIVE = true;
		public static final String[] ROLES = new String[] {"admin"};
		static {
			BEAN.setName(NAME);
			BEAN.setPassword(PASSWORD);
			BEAN.setEmail(EMAIL);
			BEAN.setActive(ACTIVE);
			BEAN.setRoles(ROLES);
		}
	}
	public static class USER_USER1 {
		public static final UserBean BEAN = new UserBean();
		public static final String NAME = "user1";
		public static final String PASSWORD = "user";
		public static final String EMAIL = "user1@lol.fr";
		public static final boolean ACTIVE = true;
		public static final String[] ROLES = new String[] {"adv"};
		static {
			BEAN.setName(NAME);
			BEAN.setPassword(PASSWORD);
			BEAN.setEmail(EMAIL);
			BEAN.setActive(ACTIVE);
			BEAN.setRoles(ROLES);
		}
	}
	public static class USER_USER2 {
		public static final UserBean BEAN = new UserBean();
		public static final String NAME = "user2";
		public static final String PASSWORD = "user2pwd";
		public static final String EMAIL = "user2@lol.fr";
		public static final boolean ACTIVE = true;
		public static final String[] ROLES = new String[] {"adv", "admin"};
		static {
			BEAN.setName(NAME);
			BEAN.setPassword(PASSWORD);
			BEAN.setEmail(EMAIL);
			BEAN.setActive(ACTIVE);
			BEAN.setRoles(ROLES);
		}
	}
	
	public static final List<UserBean> TEST_USERS = new ArrayList<UserBean>();
	static {
		TEST_USERS.add(USER_ADMIN.BEAN);
		TEST_USERS.add(USER_USER1.BEAN);
		TEST_USERS.add(USER_USER2.BEAN);
	}


	private static String USER_NAME = "newUser";
	private static int USER_ID = 0;

	public static UserBean getNextUser() {
		String name = USER_NAME + USER_ID++;
		UserBean bean = new UserBean();
		bean.setName(name);
		bean.setPassword(name);
		bean.setEmail(name + "@" + name + ".com");
		bean.setActive(true);
		bean.setRoles(new String[0]);
		return bean;
	}
	
	public static void main(String[] args) {
		MongoConnection CONN = MongoConnection.getInstance();
		CONN.getDatabase().drop();
		List<Document> list = new ArrayList<Document>();
		for (UserBean user : TestData.TEST_USERS) {
			list.add(BeanConverter.convertToDocument(user));
		}
		CONN.getCollection(MongoConstants.Users.COLLECTION).insertMany(list);
	}
}
