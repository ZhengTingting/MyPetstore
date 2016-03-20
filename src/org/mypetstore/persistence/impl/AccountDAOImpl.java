package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Account;
import org.mypetstore.persistence.AccountDAO;
import org.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Mr.Wan on 2016/3/19.
 */
public class AccountDAOImpl implements AccountDAO{

    private static final String SIGN_IN =
            "select username from signon where username = ? and password = ?;";

    private static final String GET_ACCOUNT_BY_USERNAME =
            "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL," +
            "ACCOUNT.FIRSTNAME," +
            "ACCOUNT.LASTNAME," +
            "ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY," +
            "ACCOUNT.STATE," +
            "ACCOUNT.ZIP," +
            "ACCOUNT.COUNTRY," +
            "ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption," +
            "PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT =
            "INSERT INTO ACCOUNT " +
            "(EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) " +
            "VALUES " +
            "(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_PROFILE=
            "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID, MYLISTOPT, BANNEROPT) VALUES (?, ?, ?, ?, ?)";

    private static final String INSERT_SIGNON =
            "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";

    private static final String UPDATE_ACCOUNT =
            "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?,ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ? " +
            "WHERE USERID = ?";

    private static final String UPDATE_PROFILE =
            "UPDATE PROFILE SET LANGPREF = ?,FAVCATEGORY = ?,MYLISTOPT = ?,BANNEROPT = ? WHERE USERID = ?";

    private static final String UPDATE_SIGNON =
            "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    @Override
    public Boolean signIn(String username, String password) {
        boolean result = false;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SIGN_IN);
            pStatement.setString(1,username);
            pStatement.setString(2,password);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                result = true;
            }
            DBUtil.closeConnection(connection,pStatement,resultSet);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
            }
            DBUtil.closeConnection(connection,pStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public void insertAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_ACCOUNT);
            pStatement.setString(1,account.getEmail());
            pStatement.setString(2,account.getFirstName());
            pStatement.setString(3,account.getLastName());
            pStatement.setString(4,account.getStatus());
            pStatement.setString(5,account.getAddress1());
            pStatement.setString(6,account.getAddress2());
            pStatement.setString(7,account.getCity());
            pStatement.setString(8,account.getState());
            pStatement.setString(9,account.getZip());
            pStatement.setString(10,account.getCountry());
            pStatement.setString(11,account.getPhone());
            pStatement.setString(12,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_PROFILE);
            pStatement.setString(1,account.getLanguagePreference());
            pStatement.setString(2,account.getFavouriteCategoryId());
            pStatement.setBoolean(3,account.isListOption());
            pStatement.setBoolean(4,account.isBannerOption());
            pStatement.setString(5,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_SIGNON);
            pStatement.setString(1,account.getPassword());
            pStatement.setString(2,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_ACCOUNT);
            pStatement.setString(1,account.getEmail());
            pStatement.setString(2,account.getFirstName());
            pStatement.setString(3,account.getLastName());
            pStatement.setString(4,account.getStatus());
            pStatement.setString(5,account.getAddress1());
            pStatement.setString(6,account.getAddress2());
            pStatement.setString(7,account.getCity());
            pStatement.setString(8,account.getState());
            pStatement.setString(9,account.getZip());
            pStatement.setString(10,account.getCountry());
            pStatement.setString(11,account.getPhone());
            pStatement.setString(12,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_PROFILE);
            pStatement.setString(1,account.getLanguagePreference());
            pStatement.setString(2,account.getFavouriteCategoryId());
            pStatement.setBoolean(3,account.isListOption());
            pStatement.setBoolean(4,account.isBannerOption());
            pStatement.setString(5,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_SIGNON);
            pStatement.setString(1,account.getPassword());
            pStatement.setString(2,account.getUsername());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}