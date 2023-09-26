import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test {
    public static void main(String[] args) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from t_act";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("balance"));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
    }
}
