package org.ap.web.service.users;

import java.util.List;

import org.ap.web.internal.APException;
import org.bson.Document;

public interface IUsersService {

	/**	 */
	public Document checkUser(String username, String password) throws APException;
	/**	 */
	public List<Document> getUsers() throws APException;
	/**	 */
	public Document getUserByName(String name) throws APException;
	/**	 */
	public Document getUserByEmail(String email) throws APException;
	/**	 */
	public Document createUser(Document user) throws APException;
	/**	 */
	public Document updateUser(Document user) throws APException;
	/**	 */
	public Document deleteUser(Document user) throws APException;
}
