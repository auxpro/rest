package org.ap.web.rest.servlet.auxiliaries;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.ap.web.common.EmailValidator;
import org.ap.web.internal.APException;
import org.ap.web.rest.entity.BeanConverter;
import org.ap.web.rest.entity.constant.EUserType;
import org.ap.web.rest.entity.user.AuxiliaryBean;
import org.ap.web.rest.entity.user.CredentialsBean;
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
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", EUserType.AUXILIARY.getId());
			AuxiliaryBean[] users = BeanConverter.convertToAuxiliaries(_service.getUsers(map));
			return Response.status(200).entity(users, resolveAnnotations(sc)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response createAuxiliaryJSON(SecurityContext sc, CredentialsBean bean) {
		try {
			if (!EmailValidator.getInstance().isValid(bean.getName())) throw APException.USER_NAME_INVALID;
			if (!EmailValidator.getInstance().isValid(bean.getEmail())) throw APException.USER_EMAIL_INVALID;
			AuxiliaryBean auxiliary = new AuxiliaryBean();
			auxiliary.setName(bean.getName());
			auxiliary.setEmail(bean.getEmail());
			auxiliary.setPassword(bean.getPassword());
			Document doc = BeanConverter.convertToDocument(auxiliary);
			doc = _service.createUser(doc);
			auxiliary = BeanConverter.convertToAuxiliary(doc);
			return Response.status(201).entity(auxiliary, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).build();
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
		try {
			if (_service.getUserByName(id) == null) throw APException.USER_NOT_FOUND;
			if (!sc.getUserPrincipal().getName().equals(id)) return Response.status(403).build();
			Document doc = BeanConverter.convertToDocument(bean);
			doc = _service.updateUser(doc);
			bean = BeanConverter.convertToAuxiliary(doc);
			return Response.status(200).entity(bean, resolveAnnotations(sc, bean)).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
	@Override
	public Response deleteAuxiliaryJSON(SecurityContext sc, String id) {
		try {
			_service.deleteUser(_service.getUserByEmail(id));
			return Response.status(200).build();
		} catch (APException e) {
			return sendException(e);
		}
	}
}