package com.theironyard.javawithclojure.porter;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

/**
 * Created by jeffryporter on 6/6/16.
 */

//http://localhost:4567/
    //                 /mesage
    //                 /pics
    // GET: routes return html
    // POST: routes modify the server

public class Main
{
    static User user;

    public static void main(String[] args)
    {
        Spark.init();
        Spark.get(
                "/",
                (request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null)
                    {
                        return new ModelAndView(m, "login.html");
                    }
                    else
                    {
                        m.put("name", user.name);
                        return new ModelAndView(m, "home.html");
                    }
                },
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/login",
                (request,response) -> {
                    String username = request.queryParams("username");
                    user = new User(username);
                    response.redirect("/");
                    return "";
                }
        );
    }
}
