package tools;

import org.ap.web.rest.entity.AuxiliaryBean;
import org.ap.web.rest.entity.UserBean;

import junit.framework.TestCase;

public class AssertHelper {

	public static void assertUser(UserBean expected, UserBean actual) {
		TestCase.assertEquals(expected.getName(), actual.getName());
		TestCase.assertEquals(expected.getEmail(), actual.getEmail());
		TestCase.assertEquals(expected.getActive(), actual.getActive());
		if (expected.getRoles() != null) {
			TestCase.assertEquals(expected.getRoles().length, actual.getRoles().length);
			for (int i = 0 ; i < expected.getRoles(). length ; i++) {		
				TestCase.assertEquals(expected.getRoles()[i], actual.getRoles()[i]);
			}
		} else {
			TestCase.assertNull(actual.getRoles());
		}
	}
	
	public static void assertAuxiliary(AuxiliaryBean expected, AuxiliaryBean actual) {
		assertUser(expected, actual);
		TestCase.assertEquals(expected.getFirstName(), actual.getFirstName());
		TestCase.assertEquals(expected.getLastName(), actual.getLastName());
		TestCase.assertEquals(expected.getPhone(), actual.getPhone());
	}
}
