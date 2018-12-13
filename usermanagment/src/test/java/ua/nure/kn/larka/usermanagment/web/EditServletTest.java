package test.java.ua.nure.kn.larka.usermanagment.web;

import java.text.DateFormat;
import java.util.Date;
import org.junit.Test;

import main.java.ua.nure.kn.larka.usermanagment.User;
import main.java.ua.nure.kn.larka.usermanagment.web.EditServlet;


public class EditServletTest extends MockServletTestCase {
 
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }
    
    @Test
    public void testEdit() {
        Date date = new Date();
        User user = new User(new Long(1000), "Oleg", "Ivanov", date);
        getMockUserDao().expect("update", user);
        
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    @Test
    public void testEditEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testEditEmptyLastName() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Ivanov");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testEditEmptyDate() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testEditEmptyDateIncorrect() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", "sdsada");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
} 
