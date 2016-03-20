package org.mypetstore.persistence;

import org.mypetstore.domain.LineItem;

import java.util.List;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public interface LineItemDAO {

    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}