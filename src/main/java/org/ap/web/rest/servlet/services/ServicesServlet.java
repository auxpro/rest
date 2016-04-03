package org.ap.web.rest.servlet.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.ap.web.internal.APException;
import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.ServiceBean;
import org.ap.web.rest.servlet.ServletBase;
import org.ap.web.service.users.IUsersService;
import org.ap.web.service.users.UsersMongoService;
import org.bson.Document;

@Path("/services")
public class ServicesServlet extends ServletBase implements IServicesServlet {

	/* STATIC */

	public static final String PATH = "/services";

	/* ATTRIBUTES */

	private IUsersService _service;

	/* CONSTRUCTOR */

	public ServicesServlet() throws APException {
		_service = new UsersMongoService();
	}
	public ServicesServlet(IUsersService service) throws APException {
		_service = service;
	}

	/* METHODS */

	// IUserServlet Implementation //

	@Override
	public Response getServicesJSON(SecurityContext sc) {
		try {
			ServiceBean[] users = BeanConverter.convertToServices(_service.getUsers());
			return Response.status(200).entity(users, resolveAnnotations(sc)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response createServiceJSON(SecurityContext sc, ServiceBean bean) {
		try {
			Document sad = BeanConverter.convertToDocument(bean);
			sad = _service.createUser(sad);
			bean = BeanConverter.convertToService(sad);
			return Response.status(201).entity(bean, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response getServiceJSON(SecurityContext sc, String name) {
		try {
			Document userSad = _service.getUserByName(name);
			if (userSad == null) throw APException.USER_NOT_FOUND;
			ServiceBean bean = BeanConverter.convertToService(userSad);
			return Response.status(200).entity(bean, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response updateServiceJSON(SecurityContext sc, String id, ServiceBean bean) {
		return sendException(APException.NOT_IMPLEMENTED);
	}
	@Override
	public Response deleteServiceJSON(SecurityContext sc, String id) {
		return sendException(APException.NOT_IMPLEMENTED);
	}
}