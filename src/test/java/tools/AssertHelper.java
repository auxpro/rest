package tools;

import org.ap.web.rest.entity.user.AuxiliaryBean;
import org.ap.web.rest.entity.user.ServiceBean;
import org.ap.web.rest.entity.user.UserBean;

import junit.framework.TestCase;

public class AssertHelper {

	public static void assertUser(UserBean expected, UserBean actual) {
		TestCase.assertEquals(expected.getName(), actual.getName());
		TestCase.assertEquals(expected.getEmail(), actual.getEmail());
		TestCase.assertEquals(expected.getActive(), actual.getActive());
		TestCase.assertEquals(expected.getTutoSkipped(), actual.getTutoSkipped());
		TestCase.assertEquals(expected.getType(), actual.getType());
	}
	
	public static void assertAuxiliary(AuxiliaryBean expected, AuxiliaryBean actual) {
		assertUser(expected, actual);
		TestCase.assertEquals(expected.getCivility(), actual.getCivility());
		TestCase.assertEquals(expected.getFirstName(), actual.getFirstName());
		TestCase.assertEquals(expected.getLastName(), actual.getLastName());
		TestCase.assertEquals(expected.getBirthPlace(), actual.getBirthPlace());
		TestCase.assertEquals(expected.getBirthDate(), actual.getBirthDate());
		TestCase.assertEquals(expected.getPhone(), actual.getPhone());
	}
	
	public static void assertService(ServiceBean expected, ServiceBean actual) {
		assertUser(expected, actual);
		TestCase.assertEquals(expected.getSociety(), actual.getSociety());
		TestCase.assertEquals(expected.getSiret(), actual.getSiret());
		TestCase.assertEquals(expected.getSocialReason(), actual.getSocialReason());
		TestCase.assertEquals(expected.getPhone(), actual.getPhone());
	}
}
