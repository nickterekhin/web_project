package com.controllers;

import com.database.DBContext;
import com.helpers.ResponseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        req.getRequestDispatcher(this.tmpl_dir+"/"+action.getAction()+".jsp").forward(req, resp);
    }

    protected void viewRedirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(this.tmpl_dir);
    }



}
