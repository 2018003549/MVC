package dao.impl;

import dao.AccountDAO;
import pojo.Account;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOimpl implements AccountDAO {
    public int insert(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into t_act (actno,balance)values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,act.getActno());
            ps.setDouble(2,act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int deleteByID(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_act where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public int update(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "update  t_act set actno=?,balance=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,act.getActno());
            ps.setDouble(2,act.getBalance());
            ps.setLong(3,act.getId());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public Account selectByActno(String Actno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        Account account=null;
        try{
            conn = DBUtils.getConnection();
            String sql = "select id,balance from t_act where actno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,Actno);
            rs = ps.executeQuery();
            if(rs.next()){
                Long id = Long.valueOf(rs.getString("id"));
                Double balance = Double.valueOf(rs.getString("balance"));
                account = new Account(id, Actno, balance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    public List<Account> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        List<Account> list=new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "select id,balance,actno from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Long id = Long.valueOf(rs.getString("id"));
                Double balance = Double.valueOf(rs.getString("balance"));
                String actno = rs.getString("actno");
                Account account = new Account(id, actno, balance);
                list.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
