package module;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.ap.web.rest.RestApplication;
import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.service.MongoConnection;
import org.ap.web.service.MongoConstants;
import org.bson.Document;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import tools.TestBase;
import tools.TestData;

public class TestModuleBase extends TestBase {

	/* TEST SETUP */

	private static HttpServer SERVER;
	protected static WebTarget TARGET;

	protected static MongoConnection CONN;

	
	protected static ObjectMapper MAPPER;
	@BeforeClass
	public static void setUpMapper() {
		MAPPER = new ObjectMapper();
		MAPPER.setSerializationInclusion(Include.NON_NULL);
	}
	
	@BeforeClass
	public static void startHttpServer() {
		final ResourceConfig rc = new RestApplication();
		try {
			SERVER = GrizzlyHttpServerFactory.createHttpServer(TestData.BASE_URI, rc);
		} catch (Exception e) {
			if (!e.getMessage().contains("Address already in use: bind")) {
				throw e;
			}
		}
	}
	@BeforeClass
	public static void startHttpClient() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		client.register(HttpAuthenticationFeature.basicBuilder().build());
		TARGET = client.target(TestData.BASE_URI);
	}
	@AfterClass
	public static void stopHttpServer() {
		SERVER.shutdownNow();
	}
	@BeforeClass
	public static void setUpDBClient() {
		System.out.println("opening");
		CONN = MongoConnection.getInstance();
	}
	@AfterClass
	public static void tearDownDBClient() {
		System.out.println("closing");
		CONN.close();
	}
	
	/* TEST SETUP */

	// Database handling
	@Before
	public void createDB() {						
		CONN.getDatabase().drop();
		List<Document> list = new ArrayList<Document>();
		for (UserBean user : TestData.TEST_USERS) {
			list.add(BeanConverter.convertToDocument(user));
		}
		CONN.getCollection(MongoConstants.Users.COLLECTION).insertMany(list);
	}
	@After
	public void dropDB() {						
		CONN.getDatabase().drop();		
	}
}
