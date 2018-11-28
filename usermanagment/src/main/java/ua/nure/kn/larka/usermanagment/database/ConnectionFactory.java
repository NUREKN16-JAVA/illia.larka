package main.java.ua.nure.kn.larka.usermanagment.database;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseCustomException;
}
