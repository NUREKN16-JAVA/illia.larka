package main.java.ua.nure.kn.larka.usermanagment.database;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public abstract class DaoFactory {
	private static Properties properties;

	private static final String DAO_FACTORY ="dao.kn.larka.usermanagement.db.UserDao";;
	private static DaoFactory instance;
	   
	public static synchronized DaoFactory getInstance() {
		if (instance == null){
	    		try {
				Class factoryClass = Class.forName(properties
						.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	   	}
	       return instance;
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
	
	public abstract UserDao getUserDao();
}
