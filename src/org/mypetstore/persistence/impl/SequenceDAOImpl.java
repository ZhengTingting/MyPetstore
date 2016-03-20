package org.mypetstore.persistence.impl;

import org.mypetstore.domain.Sequence;
import org.mypetstore.persistence.DBUtil;
import org.mypetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Mr.Wan on 2016/3/20.
 */
public class SequenceDAOImpl implements SequenceDAO {

    private static final String GET_SEQUENCE =
            "SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";

    private static final String UPDATE_SEQUENCE =
            "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_SEQUENCE);
            pStatement.setString(1,sequence.getName());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                sequence.setName(resultSet.getString(1));
                sequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeConnection(connection,pStatement,resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequence;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_SEQUENCE);
            pStatement.setInt(1,sequence.getNextId());
            pStatement.setString(2,sequence.getName());
            pStatement.executeUpdate();
            DBUtil.closeConnection(connection,pStatement,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}