package fr.cactus_industries.restservice;

import java.sql.SQLException;

public class Greeting {

	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() throws SQLException {
		Database db = new Database();
		db.test();
		return id;
	}

	public String getContent() {
		return content;
	}
}
