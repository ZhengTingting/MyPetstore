package org.mypetstore.persistence.impl;

import org.mypetstore.domain.LineItem;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public class LineItemDAOImpl implements LineItemDAO{

    private static final String GET_LINEITEMS_BY_ORDERID =
            "SELECT ORDERID,LINENUM AS lineNumber,ITEMID,QUANTITY,UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERT_LINEITEM =
            "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)" +
            "VALUES (?, ?,?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_LINEITEMS_BY_ORDERID);
            pStatement.setInt(1,orderId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemList.add(lineItem);
            }
            DBUtil.closeConnection(connection,pStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_LINEITEM);
            pStatement.setInt(1,lineItem.getOrderId());
            pStatement.setInt(2,lineItem.getLineNumber());
            pStatement.setString(3,lineItem.getItemId());
            pStatement.setInt(4,lineItem.getQuantity());
            pStatement.setBigDecimal(5,lineItem.getUnitPrice());
            DBUtil.closeConnection(connection, pStatement,null);
        }catch (Exception e){
        e.printStackTrace();
        }
    }
}