package main.java.ua.nure.kn.larka.usermanagment.database;

import java.util.Collection;
import main.java.ua.nure.kn.larka.usermanagment.*;

public interface UserDao {
	User create(User user) throws DatabaseCustomException;
	
	void update(User user) throws DatabaseCustomException;
	
	void delete(User user) throws DatabaseCustomException;
	
	User find(Long id) throws DatabaseCustomException;
	
	Collection findAll() throws DatabaseCustomException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}
