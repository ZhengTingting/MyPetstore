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
public class SaveAccountServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");

        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

        if(!password.equals(repeatedPassword)){
            if(account == null){
                request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request,response);
            }else{
                request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
            }
        }else{
            String firstName = request.getParameter("account.firstName");
            String lastName = request.getParameter("account.lastName");
            String email = request.getParameter("account.email");
            String phone = request.getParameter("account.phone");
            String address1 = request.getParameter("account.address1");
            String address2 = request.getParameter("account.address2");
            String city = request.getParameter("account.city");
            String state = request.getParameter("account.state");
            String zip = request.getParameter("account.zip");
            String country = request.getParameter("account.country");
            String languagePreference = request.getParameter("account.languagePreference");
            String favouriteCategoryId = request.getParameter("account.favouriteCategoryId");
            String valueOfListOption = request.getParameter("account.listOption");
            String valueOfBannerOption = request.getParameter("account.bannerOption");
            Boolean listOption;
            Boolean bannerOption;

            if(valueOfListOption == null){
                listOption = false;
            }else{
                listOption = true;
            }

            if(valueOfBannerOption == null){
                bannerOption = false;
            }else{
                bannerOption = true;
            }

            AccountService accountService = new AccountService();

            if(account == null){
                String username = request.getParameter("username");

                account = new Account();

                account.setUsername(username);
                account.setPassword(password);
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setEmail(email);
                account.setPhone(phone);
                account.setAddress1(address1);
                account.setAddress2(address2);
                account.setCity(city);
                account.setState(state);
                account.setZip(zip);
                account.setCountry(country);
                account.setLanguagePreference(languagePreference);
                account.setFavouriteCategoryId(favouriteCategoryId);
                account.setListOption(listOption);
                account.setBannerOption(bannerOption);

                accountService.insertAccount(account);
                request.getRequestDispatcher(MAIN).forward(request,response);
            }else{
                account.setPassword(password);
                account.setFirstName(firstName);
                account.setLastName(lastName);
                account.setEmail(email);
                account.setPhone(phone);
                account.setAddress1(address1);
                account.setAddress2(address2);
                account.setCity(city);
                account.setState(state);
                account.setZip(zip);
                account.setCountry(country);
                account.setLanguagePreference(languagePreference);
                account.setFavouriteCategoryId(favouriteCategoryId);
                account.setListOption(listOption);
                account.setBannerOption(bannerOption);

                accountService.updateAccount(account);
                request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
