package service.impl;

import dao.AccountDAO;
import dao.impl.AccountDAOimpl;
import pojo.Account;
import service.AccountService;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 转账等相关业务类
 * 只处理业务逻辑
 */
public class AccountServiceimpl implements AccountService {
    AccountDAO accountDAO = new AccountDAOimpl();

    /**
     * @param fromActno 转出账户
     * @param toActno   转入账户
     * @param money     转出金额
     */
    public void transfer(String fromActno, String toActno, double money) throws RuntimeException {
        try (Connection connection = DBUtils.getConnection()) {
            connection.setAutoCommit(false);
            //先获取转出账户
            Account fromACT = accountDAO.selectByActno(fromActno);
            //检查转出账户余额是否充足
            if (fromACT.getBalance() < money) {
                throw new RuntimeException("用户余额不足");
            }
            //执行到这里说明余额充足，则获取转入对象
            Account toACT = accountDAO.selectByActno(toActno);
            //修改两个对象的余额
            fromACT.setBalance(fromACT.getBalance() - money);
            toACT.setBalance(toACT.getBalance() + money);
            //修改数据库的数据
            int count = accountDAO.update(fromACT);
            count += accountDAO.update(toACT);
            if (count != 2) {
                throw new RuntimeException("转账失败");
            }
            connection.commit();//提交事务
        } catch (SQLException throwables) {
            throw new RuntimeException("转账异常");
        }
    }
}
