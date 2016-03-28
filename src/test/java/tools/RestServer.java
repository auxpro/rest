package tools;

import org.ap.web.rest.RestApplication;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RestServer {

	public static void main(String[] args) {
		final ResourceConfig rc = new RestApplication();
		try {
			GrizzlyHttpServerFactory.createHttpServer(TestData.BASE_URI, rc);
		} catch (Exception e) {
			if (!e.getMessage().contains("Address already in use: bind")) {
				throw e;
			}
		}
	}
}
