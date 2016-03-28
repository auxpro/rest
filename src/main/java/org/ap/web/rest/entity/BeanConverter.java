package org.ap.web.rest.entity;

import java.util.ArrayList;
import java.util.List;

import org.ap.web.internal.APException;
import org.ap.web.service.MongoConstants;
import org.bson.Document;

public class BeanConverter {
	
	public static ErrorBean convert(APException ape) {
		ErrorBean exBean = new ErrorBean();
		ErrorDetailsBean bean = new ErrorDetailsBean();
		bean.setCode(ape.getCode());
		bean.setMessage(ape.getMessage());
		exBean.setError(bean);
		return exBean;
	}
	public static UserBean[] convertToUsers(List<Document> users) {
		UserBean[] beans = new UserBean[users.size()];
		for (int i = 0 ; i < beans.length ; i++) {
			beans[i] = convertToUser(users.get(i));
		}
		return beans;
	}
	public static UserBean convertToUser(Document user) {
		UserBean bean = new UserBean();
		bean.setName(user.getString(MongoConstants.Users.NAME));
		bean.setEmail(user.getString(MongoConstants.Users.EMAIL));
		//!\\ PASSWORD IS NEVER LOADED FROM DB
		bean.setActive(user.getBoolean(MongoConstants.Users.ACTIVE));
		bean.setRoles(getArray(user, MongoConstants.Users.ROLES));
		return bean;
	}
	public static Document convertToDocument(UserBean user){
		return new Document()
				.append(MongoConstants.Users.NAME, user.getName())
				.append(MongoConstants.Users.EMAIL, user.getEmail())
				.append(MongoConstants.Users.PASSWORD, user.getPassword())
				.append(MongoConstants.Users.ACTIVE, user.getActive())
				.append(MongoConstants.Users.ROLES, convertToArrayList(user.getRoles()));
	}
	private static List<String> convertToArrayList(String[] in){
		List<String> list = new ArrayList<String>();
		if (in == null)
			return list;
		for (String i : in){
			list.add(i);
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	private static String[] getArray(Document doc, String key){
		ArrayList<String> list = (ArrayList<String>)doc.get(key);
		System.out.println(list);
		return list.toArray(new String[list.size()]);
	}	

}