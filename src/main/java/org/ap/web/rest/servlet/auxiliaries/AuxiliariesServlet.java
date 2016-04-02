package org.ap.web.rest.servlet.auxiliaries;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.ap.web.internal.APException;
import org.ap.web.rest.entity.AuxiliaryBean;
import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.UserBean;
import org.ap.web.rest.servlet.ServletBase;
import org.ap.web.service.users.IUsersService;
import org.ap.web.service.users.UsersMongoService;
import org.bson.Document;

@Path("/auxiliaries")
public class AuxiliariesServlet extends ServletBase implements IAuxiliariesServlet {

	/* STATIC */

	public static final String PATH = "/auxiliaries";  

	/* ATTRIBUTES */

	private IUsersService _service;

	/* CONSTRUCTOR */

	public AuxiliariesServlet() throws APException {
		_service = new UsersMongoService();
	}
	public AuxiliariesServlet(IUsersService service) throws APException {
		_service = service;
	}

	/* METHODS */

	// IUserServlet Implementation //

	@Override
	public Response getAuxiliariesJSON(SecurityContext sc) {
		try {
			AuxiliaryBean[] users = BeanConverter.convertToAuxiliaries(_service.getUsers());
			return Response.status(200).entity(users, resolveAnnotations(sc)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response createAuxiliaryJSON(SecurityContext sc, AuxiliaryBean bean) {
		try {
			Document aux = BeanConverter.convertToDocument(bean);
			aux = _service.createUser(aux);
			bean = BeanConverter.convertToAuxiliary(aux);
			return Response.status(201).entity(bean, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response getAuxiliaryJSON(SecurityContext sc, String name) {
		try {
			Document userAux = _service.getUserByName(name);
			if (userAux == null) throw APException.USER_NOT_FOUND;
			AuxiliaryBean bean = BeanConverter.convertToAuxiliary(userAux);
			return Response.status(200).entity(bean, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response updateAuxiliaryJSON(SecurityContext sc, String id, AuxiliaryBean bean) {
		return sendException(APException.NOT_IMPLEMENTED);
	}
	@Override
	public Response deleteAuxiliaryJSON(SecurityContext sc, String id) {
		return sendException(APException.NOT_IMPLEMENTED);
	}
}