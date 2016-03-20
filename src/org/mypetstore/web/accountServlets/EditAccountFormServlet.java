package org.mypetstore.web.accountServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public class EditAccountFormServlet extends HttpServlet {

    private static final String EDIT_ACCOUNT_FORM = "WEB-INF/jsp/account/EditAccountForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
    }
}