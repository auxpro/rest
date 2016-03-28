package org.ap.web.rest.servlet.auxiliaries;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.ap.web.rest.entity.AuxiliaryBean;

/**
 * This interface describes the auxiliaries servlet features.
 * The following actions are available:
 *  - /auxiliaries         GET    > list system auxiliaries
 *  - /auxiliaries/{auxId} GET    > retrieve an auxiliary details
 *  - /auxiliaries/{auxId} POST   > create a new auxiliary 
 *  - /auxiliaries/{auxId} PUT    > update an existing auxiliary
 *  - /auxiliaries/{auxId} DELETE > delete an auxiliary
 */
public interface IAuxiliariesServlet {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAuxiliariesJSON(@Context SecurityContext sc);
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createAuxiliaryJSON(@Context SecurityContext sc, AuxiliaryBean auxiliary);
	
	@GET
	@Path("{auxId}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getAuxiliaryJSON(@Context SecurityContext sc, @PathParam("auxId") final String id);

	@PUT
	@Path("{auxId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateAuxiliaryJSON(@Context SecurityContext sc, @PathParam("auxId") final String id, AuxiliaryBean auxiliary);
	
	@DELETE
	@RolesAllowed("admin")
	@Path("{auxId}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteAuxiliaryJSON(@Context SecurityContext sc, @PathParam("auxId") final String id);
}
