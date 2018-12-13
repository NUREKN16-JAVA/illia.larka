package main.java.ua.nure.kn.larka.usermanagment.web;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.User;

import main.java.ua.nure.kn.larka.usermanagment.database.DaoFactory;
import main.java.ua.nure.kn.larka.usermanagment.database.DatabaseCustomException;

public class BrowseServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	// check if any button was clicked, and if it was then 
	    // execute the appropriate operation
    	
        if (req.getParameter("addButton") != null) {
            add(req, resp);
        } else if (req.getParameter("editButton") != null) {
            edit(req, resp);
        } else if (req.getParameter("deleteButton") != null) {
            delete(req, resp);
        } else if (req.getParameter("detailsButton") != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }

    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().length() == 0) {
            req.setAttribute("error", "You must select a user");
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(idStr));
            req.getSession().setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "ERROR:" + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/edit").forward(req, resp);
        
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add").forward(req, resp);
        
    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection users;
        try {
            users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseCustomException e) {
            throw new ServletException(e);
        }
        
    }
} 