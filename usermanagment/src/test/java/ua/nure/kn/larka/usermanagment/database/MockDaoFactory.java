package test.java.ua.nure.kn.larka.usermanagment.database;

import com.mockobjects.dynamic.Mock;

import main.java.ua.nure.kn.larka.usermanagment.database.DaoFactory;
import main.java.ua.nure.kn.larka.usermanagment.database.UserDao;

public class MockDaoFactory extends DaoFactory {
	private Mock mockUserDao;
	
	public MockDaoFactory(){
		mockUserDao = new Mock(UserDao.class);
	}
	
	public Mock getMockUserDao(){
		return mockUserDao;
	}
	
	@Override
	public UserDao getUserDao() {
		return (UserDao) mockUserDao.proxy();
	}
}