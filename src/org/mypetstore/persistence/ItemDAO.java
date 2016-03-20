package org.mypetstore.persistence;

import org.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Wan on 2016/3/18.
 */
public interface ItemDAO {
    void updateInventoryQuantity(Map<String, Object> param);
    int getInventoryQuantity(String itemId);
    List<Item> getItemListByProduct(String productId);
    Item getItem(String itemId);
}