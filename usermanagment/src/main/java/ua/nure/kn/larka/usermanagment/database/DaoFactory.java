package main.java.ua.nure.kn.larka.usermanagment.database;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class DaoFactory {
	private static final String USER_DAO = "dao.kn.larka.usermanagement.db.UserDao";
	private Properties properties;
	private final static DaoFactory INSTANCE = new DaoFactory();
	    
    public static DaoFactory getInstance() {
        return INSTANCE;
    }
    
	public DaoFactory() {
		properties = new Properties();
		try {
			String propFileName = "settings.properties";
 
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				properties.load(inputStream);
			}
			
			//properties.load(getClass().getClassLoader().getResourceAsStream("resources/settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void init (Properties properties){
		this.properties = properties;
	}
	
	private ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImplementation(properties);
	}
	
	public UserDao getUserDao() {
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
