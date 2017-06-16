package com.controllers;

import com.database.DBContext;
import com.helpers.NotificationType;
import com.helpers.ResponseAction;
import com.services.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Nick on 12.06.17.
 */
public abstract class BaseController extends HttpServlet {

    protected DBContext dbContext;
    protected String tmpl_dir;

    protected void initDbContext()
    {
        this.dbContext = new DBContext();
    }

    protected abstract void initTemplateDir();


    protected void view(HttpServletRequest req, HttpServletResponse resp, ResponseAction action) throws ServletException, IOException {
        HttpSession session = req.getSession();
        NotificationService message = (NotificationService) session.getAttribute("message");
        if(message!=null)
        {
            message.show(req, NotificationType.SUCCESS);
            session.removeAttribute("message");
        }
        req.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
                req.getRequestDispatcher(this.tmpl_dir + "/" + action.getAction() + ".jsp").forward(req, resp);
    }

    protected void viewRedirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(this.tmpl_dir);
    }

    protected void viewRedirect(HttpServletRequest req, HttpServletResponse resp, String respMessage) throws IOException {
        if(respMessage==null || respMessage.isEmpty())
            this.viewRedirect(resp);
        HttpSession session = req.getSession();
        session.setAttribute("message",new NotificationService(respMessage));
        resp.sendRedirect(this.tmpl_dir);
    }


}
