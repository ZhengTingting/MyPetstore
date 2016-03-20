package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Category;
import org.mypetstore.persistence.CategoryDAO;
import org.mypetstore.persistence.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Mr.Wan on 2016/3/18.
 */
public class CategoryDAOImpl implements CategoryDAO {

    private static final String GET_CATEGORY_LIST =
            "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY =
            "SELECT CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<Category>();
        try{
            Connection connction = DBUtil.getConnection();
            PreparedStatement pStatement = connction.prepareStatement(GET_CATEGORY_LIST);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
                categoryList.add(category);
            }
            DBUtil.closeConnection(connction,pStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connction = DBUtil.getConnection();
            PreparedStatement pStatement = connction.prepareStatement(GET_CATEGORY);
            pStatement.setString(1,categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                category = new Category();
                category.setCategoryId(resultSet.getString(1));
                category.setName(resultSet.getString(2));
                category.setDescription(resultSet.getString(3));
            }
            DBUtil.closeConnection(connction,pStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}