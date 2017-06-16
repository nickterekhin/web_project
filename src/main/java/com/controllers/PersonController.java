package com.controllers;

import com.domain.Person;
import com.helpers.NotificationType;
import com.helpers.ResponseAction;
import com.services.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nick on 11.06.17.
 */
public class PersonController extends BaseController {


    private Person p;

    @Override
    public void init() throws ServletException {
        this.initDbContext();
        this.initTemplateDir();
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
                            try {
                                this.userValidator(p);
                                dbContext.getPersons().create(p);
                                this.viewRedirect(req,resp,"User Added Successfully");
                            }catch(NotificationService notify)
                            {
                                notify.show(req,NotificationType.ERROR);
                            }
                        }
                        if(!resp.isCommitted())
                        {
                            req.setAttribute("person", p);
                            this.view(req, resp, ResponseAction.ADD);
                        }
                    break;
                case EDIT:
                    String id = req.getParameter("id");
                    p = dbContext.getPersons().getById(Long.valueOf(id));
                    if(req.getMethod().equals("POST"))
                    {
                        p.setName(req.getParameter("name"));
                        p.setAge(Integer.valueOf(req.getParameter("age")));
                        try {
                            this.userValidator(p);
                            dbContext.getPersons().update(p);
                            this.viewRedirect(req,resp,"User Updated Successfully");
                        }catch (NotificationService notify)
                        {
                            notify.show(req, NotificationType.ERROR);
                        }
                    }
                    if(!resp.isCommitted()) {
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


    private void userValidator(Person p) throws NotificationService {
        if(p.getName().isEmpty())
        {
            throw new NotificationService("User Name is required");
        }

        if(p.getAge()==0)
        {
            throw new NotificationService("Age field is required");
        }


        Pattern pattern = Pattern.compile("[./*+?|()'><\\\"]");
        Matcher mName = pattern.matcher(p.getName());
        if(mName.find())
        {
            throw new NotificationService("Name has illegal characters");
        }
        Matcher mAge = pattern.matcher(String.valueOf(p.getAge()));
        if(mAge.find())
        {
            throw  new NotificationService("Age has illegal characters");
        }

        if((p.getId()==0 && dbContext.getPersons().getByName(p.getName())!=null) || (p.getId()!=0 && dbContext.getPersons().getByName(p.getName(),p.getId())!=null))
        {
            throw new NotificationService("Person with this Name already Exists");
        }



    }
}
