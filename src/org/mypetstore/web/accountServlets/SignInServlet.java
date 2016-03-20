package org.mypetstore.web.accountServlets;

import org.mypetstore.domain.Account;
import org.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public class SignInServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGN_IN_FORM = "/WEB-INF/jsp/account/SignInForm.jsp";
    private static final String ERR_MSG = "Invalid username or password.  Signon failed.";

    private String username;
    private String password;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        AccountService accountService = new AccountService();
        HttpSession session = request.getSession();

        Account account = accountService.getAccount(username,password);

        if(account == null){
            session.setAttribute("message",ERR_MSG);
            request.getRequestDispatcher(SIGN_IN_FORM).forward(request,response);
        }else{
            session.setAttribute("account",account);
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}