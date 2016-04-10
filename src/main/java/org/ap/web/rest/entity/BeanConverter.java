package org.ap.web.rest.entity;

import java.util.List;

import org.ap.web.internal.APException;
import org.ap.web.rest.entity.constant.EAuxCivility;
import org.ap.web.rest.entity.constant.EUserType;
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
		bean.setType(EUserType.byId(user.getString(MongoConstants.Users.TYPE)).getId());
		return bean;
	}
	public static Document convertToDocument(UserBean user){
		return new Document()
				.append(MongoConstants.Users.NAME, user.getName())
				.append(MongoConstants.Users.EMAIL, user.getEmail())
				.append(MongoConstants.Users.PASSWORD, user.getPassword())
				.append(MongoConstants.Users.ACTIVE, user.getActive())
				.append(MongoConstants.Users.TYPE, EUserType.byId(user.getType()).getId());
	}
	
	public static AuxiliaryBean[] convertToAuxiliaries(List<Document> auxs) {
		AuxiliaryBean[] beans = new AuxiliaryBean[auxs.size()];
		for (int i = 0 ; i < beans.length ; i++) {
			beans[i] = convertToAuxiliary(auxs.get(i));
		}
		return beans;
	}
	public static AuxiliaryBean convertToAuxiliary(Document aux) {
		AuxiliaryBean bean = new AuxiliaryBean();
		bean.setName(aux.getString(MongoConstants.Users.NAME));
		bean.setEmail(aux.getString(MongoConstants.Users.EMAIL));
		//!\\ PASSWORD IS NEVER LOADED FROM DB
		bean.setActive(aux.getBoolean(MongoConstants.Users.ACTIVE));
		bean.setCivility(aux.getString(MongoConstants.Auxiliaries.CIVILITY));
		bean.setFirstName(aux.getString(MongoConstants.Auxiliaries.FIRST_NAME));
		bean.setLastName(aux.getString(MongoConstants.Auxiliaries.LAST_NAME));
		bean.setPhone(aux.getString(MongoConstants.Auxiliaries.PHONE));
		Object o = aux.get(MongoConstants.Auxiliaries.ADDRESS);
		System.out.println("address: " + o);
		System.out.println(aux.toJson());
		return bean;
	}
	public static Document convertToDocument(AuxiliaryBean aux){
		return new Document()
				.append(MongoConstants.Auxiliaries.NAME, aux.getName())
				.append(MongoConstants.Auxiliaries.EMAIL, aux.getEmail())
				.append(MongoConstants.Auxiliaries.PASSWORD, aux.getPassword())
				.append(MongoConstants.Auxiliaries.ACTIVE, aux.getActive())
				.append(MongoConstants.Auxiliaries.TYPE, EUserType.byId(aux.getType()).getId())
				.append(MongoConstants.Auxiliaries.CIVILITY, EAuxCivility.fromString(aux.getCivility()).getId())
				.append(MongoConstants.Auxiliaries.FIRST_NAME, aux.getFirstName())
				.append(MongoConstants.Auxiliaries.LAST_NAME, aux.getLastName())
				.append(MongoConstants.Auxiliaries.PHONE, aux.getPhone());		
	}
	
	public static ServiceBean[] convertToServices(List<Document> sads) {
		ServiceBean[] beans = new ServiceBean[sads.size()];
		for (int i = 0 ; i < beans.length ; i++) {
			beans[i] = convertToService(sads.get(i));
		}
		return beans;
	}
	public static ServiceBean convertToService(Document sad) {
		ServiceBean bean = new ServiceBean();
		bean.setName(sad.getString(MongoConstants.Services.NAME));
		bean.setEmail(sad.getString(MongoConstants.Services.EMAIL));
		//!\\ PASSWORD IS NEVER LOADED FROM DB
		bean.setActive(sad.getBoolean(MongoConstants.Services.ACTIVE));
		bean.setSociety(sad.getString(MongoConstants.Services.SOCIETY));
		bean.setPhone(sad.getString(MongoConstants.Services.PHONE));
		return bean;
	}
	public static Document convertToDocument(ServiceBean sad){
		return new Document()
				.append(MongoConstants.Services.NAME, sad.getName())
				.append(MongoConstants.Services.EMAIL, sad.getEmail())
				.append(MongoConstants.Services.PASSWORD, sad.getPassword())
				.append(MongoConstants.Services.ACTIVE, sad.getActive())
				.append(MongoConstants.Services.TYPE, EUserType.byId(sad.getType()).getId())
				.append(MongoConstants.Services.SOCIETY, sad.getSociety())
				.append(MongoConstants.Services.PHONE, sad.getPhone());		
	}
	
	/*
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
	*/

}