package dao.impl;

import dao.IOrderDetailDAO;
import model.OrderDetailModel;
import util.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements IOrderDetailDAO {

    @Override
    public void save(OrderDetailModel model) {
        try {
            String sql = "INSERT INTO OrderDetail (orderid,productid,quantity) VALUES (?, ?, ?)";
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, model.getOrderId());
            statement.setInt(2, model.getProductId());
            statement.setInt(3, model.getQuantity());
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetailModel> findByOrderId(int orderId) {
        List<OrderDetailModel> results = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail AS od INNER JOIN Orders AS o ON od.orderid = o.id";
        sql += " INNER JOIN Product AS p ON p.id = od.productid ";
        sql += " WHERE od.orderid = " + orderId + "";
        try {
            Connection connection = ConnectionUtils.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setCustomerName(resultSet.getString("ordername"));
                orderDetailModel.setProductName(resultSet.getString("name"));
                orderDetailModel.setQuantity(resultSet.getInt("quantity"));
                orderDetailModel.setPrice(resultSet.getInt("price"));
                results.add(orderDetailModel);
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void deleteByProduct(int productId) {
        try {
            String sql = "DELETE FROM OrderDetail where productid = ?";
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteByOrder(int orderId) {
        try {
            String sql = "DELETE FROM OrderDetail where orderid = ?";
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
