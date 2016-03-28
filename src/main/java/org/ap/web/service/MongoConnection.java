package org.ap.web.service;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection implements IMongoConnection {

	/* STATIC */

	public static final String HOST = "localhost";
	public static final int    PORT = 27017;
	public static final String NAME = "test-db";
	
	private static MongoConnection INSTANCE;
	public static MongoConnection getInstance() {
		if (INSTANCE == null) { INSTANCE = Factory.open(HOST, PORT, NAME); }
		return INSTANCE;
	}

	/* ATTRIBUTES */

	private MongoClient _client;
	private MongoDatabase _db;

	/* CONSTRUCTOR */
	
	public MongoConnection(MongoClient client, MongoDatabase db) {
		_client = client;
		_db = db;
	}
	public static class Factory {
		public static MongoConnection open(String host, int port, String db) {
			MongoClient client = new MongoClient(host, port);
			return new MongoConnection(client, client.getDatabase(db));
		}
	}
	
	/* METHODS */
	
	@Override public MongoClient getClient() { return _client; }
	@Override public MongoDatabase getDatabase() { return _db; }
	@Override public MongoCollection<Document> getCollection(String name) { return _db.getCollection(name); }
	@Override public void close() { _client.close(); }
}
