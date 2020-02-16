package dao.impl;

import dao.IProductDAO;
import model.ProductModel;
import util.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    
    @Override
    public List<ProductModel> findAll(Integer status) {
        List<ProductModel> results = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        if (status != null) {
            sql += " WHERE status = "+status+"";
        }
        try {
            Connection connection = ConnectionUtils.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setDescription(resultSet.getString("description"));
                productModel.setManufacturerId(resultSet.getInt("manufacturerid"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setStatus(resultSet.getInt("status"));
                productModel.setQuantity(resultSet.getInt("quantity"));
                productModel.setPicture(resultSet.getString("picture"));
                results.add(productModel);
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
    public void save(ProductModel model) {
        try {
            String sql = "";
            if (model.getId() == 0) {
                sql = "INSERT INTO Product (name,description,price,quantity,status,manufacturerid,picture) VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Product SET name = ?, description = ?, price = ?, quantity = ?, status = ?, manufacturerid = ?, picture = ? WHERE id = ?";
            }
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setInt(3, model.getPrice());
            statement.setInt(4, model.getQuantity());
            statement.setInt(5, model.getStatus());
            statement.setInt(6, model.getManufacturerId());
            statement.setString(7, model.getPicture());
            if (model.getId() != 0) {
                statement.setInt(8, model.getId());
            }
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public ProductModel findById(int id) {
        ProductModel productModel = null;
        String sql = "SELECT * FROM Product WHERE id = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setDescription(resultSet.getString("description"));
                productModel.setManufacturerId(resultSet.getInt("manufacturerid"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setStatus(resultSet.getInt("status"));
                productModel.setQuantity(resultSet.getInt("quantity"));
                productModel.setPicture(resultSet.getString("picture"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productModel;
    }

    @Override
    public List<ProductModel> findByManufacturerId(int manufacturerId) {
        List<ProductModel> productModels = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE manufacturerid = ? AND status = 1";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, manufacturerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setName(resultSet.getString("name"));
                productModel.setDescription(resultSet.getString("description"));
                productModel.setManufacturerId(resultSet.getInt("manufacturerid"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setStatus(resultSet.getInt("status"));
                productModel.setQuantity(resultSet.getInt("quantity"));
                productModel.setPicture(resultSet.getString("picture"));
                productModels.add(productModel);
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productModels;
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM Product where id = ?";
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
