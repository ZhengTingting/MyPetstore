package org.mypetstore.web.catalogServlets;

import java.io.IOException;

/**
 * Created by Mr.Wan on 2016/3/18.
 */
public class ViewMainServlet extends javax.servlet.http.HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher(MAIN).forward(request,response);
    }
}