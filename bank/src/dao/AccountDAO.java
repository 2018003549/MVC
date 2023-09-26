package dao;

import pojo.Account;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AccountDAO {
    public int insert(Account act);

    public int deleteByID(String id) ;

    public int update(Account act) ;

    public Account selectByActno(String Actno);

    public List<Account> selectAll() ;
}
