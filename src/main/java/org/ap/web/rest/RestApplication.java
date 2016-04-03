package org.ap.web.rest;

import javax.ws.rs.ApplicationPath;

import org.ap.web.rest.filter.AuthorizationRequestFilter;
import org.ap.web.rest.filter.HeadersResponseFilter;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SecurityEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class RestApplication extends ResourceConfig {

	/* STATIC */

	/** Enables the application logs */
	public static boolean LOGGING_MODE = true;
	
	/* ATTRIBUTES */
	
	/* CONSTRUCTORS */
	
	/**	 */
	public RestApplication() {
		
		packages("org.ap.web.rest.servlet");
		register(JacksonFeature.class);
		//register(JacksonJsonProvider.class);
		register(AuthorizationRequestFilter.class);
		register(HeadersResponseFilter.class);
		register(SecurityEntityFilteringFeature.class);
		register(EntityFilteringFeature.class);
		if (LOGGING_MODE) {
			register(LoggingFilter.class);
		}
	}
	
	/* METHODS */
	
}
