package test.java.ua.nure.kn.larka.usermanagment.database;

import main.java.ua.nure.kn.larka.usermanagment.database.*;
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase{
	public void testGetUserDao() {
		try {
			// First
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactoryinstance is null", daoFactory);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
}
