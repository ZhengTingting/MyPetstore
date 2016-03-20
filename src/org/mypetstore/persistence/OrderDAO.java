package org.mypetstore.persistence;

import org.mypetstore.domain.Order;

import java.util.List;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public interface OrderDAO {

    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);
}
