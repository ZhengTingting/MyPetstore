package org.mypetstore.web.catalogServlets;

import org.mypetstore.domain.Item;
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
public class ViewProductServlet extends HttpServlet {

    private static final String PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";

    private String productId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product = service.getProduct(productId);
        List<Item> itemListList = service.getItemListByProduct(productId);

        HttpSession session = request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemListList);

        request.getRequestDispatcher(PRODUCT).forward(request,response);
    }
}