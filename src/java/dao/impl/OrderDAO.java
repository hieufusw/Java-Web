/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.IOrderDAO;
import model.OrdersModel;
import util.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    @Override
    public int save(OrdersModel model) {
        int key = 0;
        try {
            String sql = "";
            if (model.getId() != 0) {
                sql = "UPDATE Orders SET ordername = ?, address = ?, phone = ?, total = ?, status = ?, customerid = ?, date = ? WHERE id = ?";
            } else {
                sql = "INSERT INTO Orders (ordername,address,phone,total,status,customerid,date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            }         
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getOrderName());
            statement.setString(2, model.getAddress());
            statement.setString(3, model.getPhone());
            statement.setInt(4, model.getTotal());
            statement.setInt(5, model.getStatus());
            statement.setInt(6, model.getCustomerId()); 
            statement.setString(7, model.getDate());
            if (model.getId() != 0) {
                statement.setInt(8, model.getId()); 
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public List<OrdersModel> findAll() {
        List<OrdersModel> results = new ArrayList<>();
        String sql = "SELECT * FROM Orders AS o INNER JOIN Users AS u ON u.id = o.customerid";
        try {
            Connection connection = ConnectionUtils.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                OrdersModel ordersModel = new OrdersModel();
                ordersModel.setId(resultSet.getInt("id"));
                ordersModel.setCustomerName(resultSet.getString("ordername"));
                ordersModel.setStatus(resultSet.getInt("status"));
                ordersModel.setAddress(resultSet.getString("address"));
                ordersModel.setCustomerId(resultSet.getInt("customerid"));
                ordersModel.setTotal(resultSet.getInt("total"));
                ordersModel.setPhone(resultSet.getString("phone"));
                ordersModel.setDate(resultSet.getString("date"));
                results.add(ordersModel);
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
    public OrdersModel findById(int id) {
        OrdersModel ordersModel = null;
        String sql = "SELECT * FROM Orders WHERE id = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ordersModel = new OrdersModel();
                ordersModel.setId(resultSet.getInt("id"));
                ordersModel.setAddress(resultSet.getString("address"));
                ordersModel.setOrderName(resultSet.getString("orderName"));
                ordersModel.setPhone(resultSet.getString("phone"));
                ordersModel.setTotal(resultSet.getInt("total"));
                ordersModel.setStatus(resultSet.getInt("status"));
                ordersModel.setCustomerId(resultSet.getInt("customerid"));
                ordersModel.setDate(resultSet.getString("date"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersModel;
    }
}
