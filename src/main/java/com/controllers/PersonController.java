package com.controllers;

import com.domain.Person;
import com.helpers.ResponseAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nick on 11.06.17.
 */
public class PersonController extends BaseController {


    private Person p;

    @Override
    public void init() throws ServletException {
        this.initDbContext();
        this.initTemplateDir();
        //personList = dbContext.getPersons().getAll();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResponseAction action = ResponseAction.getResponseAction(req.getParameter("action"));


            switch(action) {
                case DELETE:
                    dbContext.getPersons().delete(Long.valueOf(req.getParameter("id")));
                   this.viewRedirect(resp);
                    break;
                case ADD:
                    p = new Person();
                    if(req.getMethod().equals("POST"))
                    {
                        p.setName(req.getParameter("name"));
                        p.setAge(Integer.valueOf(req.getParameter("age")));
                        dbContext.getPersons().create(p);
                        this.viewRedirect(resp);
                    }
                    else {
                        req.setAttribute("person", p);
                        this.view(req,resp,ResponseAction.ADD);

                    }
                    break;
                case EDIT:
                    String id = req.getParameter("id");
                    p = dbContext.getPersons().getById(Long.valueOf(id));
                    if(req.getMethod().equals("POST"))
                    {
                        p.setName(req.getParameter("name"));
                        p.setAge(Integer.valueOf(req.getParameter("age")));
                        dbContext.getPersons().update(p);
                        this.viewRedirect(resp);
                    }else {
                        req.setAttribute("person", p);
                        this.view(req, resp, ResponseAction.EDIT);
                    }
                    break;
                default:
                    req.setAttribute("persons", dbContext.getPersons().getAll());
                    this.view(req, resp, ResponseAction.LIST);
            }

    }

    @Override
    protected void initTemplateDir() {
        this.tmpl_dir = "/persons";
    }
}
