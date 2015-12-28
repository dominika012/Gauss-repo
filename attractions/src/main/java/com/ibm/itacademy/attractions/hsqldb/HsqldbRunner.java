package com.ibm.itacademy.attractions.hsqldb;

import org.hsqldb.server.Server;

public class HsqldbRunner {
	public static void main(String[] args) {
		Server server = new Server();
		server.setDatabasePath(0, "db/city");
		server.setDatabaseName(0, "city");
		server.start();
	}
}
