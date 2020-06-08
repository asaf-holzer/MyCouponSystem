package com.asafh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
	private static final String URL = "jdbc:mysql://localhost:3306/coupons_system?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";
	private static final String userName = "root";
	private static final String password = "Asaf1980#Holzer5740*";

	public static String getUrl() {
		return URL;
	}

	public static String getUsername() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	private Stack<Connection> connections = new Stack<>();

	private static ConnectionPool instance = null;// = new ConnectionPool();

	private ConnectionPool() {
		System.out.print("Creating connection");
		for (int i = 1; i <= 10; i++) {
			System.out.print(" #" + i + " ");
			try {
				Connection conn = DriverManager.getConnection(URL, userName, password);
				connections.push(conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println();
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}

	public void returnConnection(Connection conn) {

		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	public void closeAllConnection() throws InterruptedException {

		synchronized (connections) {

			while (connections.size() < 10) {
				connections.wait();
			}
			int count = 1;
			System.out.print("Cloasing connection");
			for (Connection conn : connections) {

				try {

					conn.close();
					System.out.print(" #" + (count++) + " ");

				} catch (Exception e) {
				}
			}
			System.out.println();
		}
	}
}