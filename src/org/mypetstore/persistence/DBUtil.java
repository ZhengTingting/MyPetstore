package org.mypetstore.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by Mr.Wan on 2016/3/18.
 */
public class DBUtil {

    private static String driver;
    private static String dburl;
    private static String user;
    private static String password;

    private static Connection conn;

    private static final DBUtil dbUtil = new DBUtil();

    static {
        Properties prop = new Properties();
        try{
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("org/mypetstore/persistence/dbconfig.properties");
            prop.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }

        driver = prop.getProperty("driver");
        dburl = prop.getProperty("dburl");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    private DBUtil(){}

    public static DBUtil getInstance(){
        return dbUtil;
    }

    public static Connection getConnection(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(dburl,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection con , PreparedStatement ps, ResultSet rs){
        try {
            if(rs !=  null)
                rs.close();
            if(ps != null)
                ps.close();
            if(con != null)
                con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}