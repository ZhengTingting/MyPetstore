package org.mypetstore.web.catalogServlets;

import org.mypetstore.domain.Category;
import org.mypetstore.domain.Product;
import org.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mr.Wan on 2016/3/19.
 */
public class ViewCategoryServlet extends HttpServlet {

    private static final String CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";

    private String categoryId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId = request.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);

        HttpSession session = request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(CATEGORY).forward(request,response);
    }
}
