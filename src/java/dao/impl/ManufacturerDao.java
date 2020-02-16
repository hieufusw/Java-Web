package dao.impl;

import dao.IManufacturerDao;
import model.ManufacturerModel;
import model.UserModel;
import util.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao implements IManufacturerDao {

    @Override
    public List<ManufacturerModel> findAll(Integer status) {
        List<ManufacturerModel> results = new ArrayList<>();
        String sql = "SELECT * FROM Manufacturer";
        if (status != null) {
            sql += " WHERE status = "+status+"";
        }
        try {
            Connection connection = ConnectionUtils.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ManufacturerModel manufacturerModel = new ManufacturerModel();
                manufacturerModel.setId(resultSet.getInt("id"));
                manufacturerModel.setName(resultSet.getString("name"));
                manufacturerModel.setWebsite(resultSet.getString("website"));
                manufacturerModel.setStatus(resultSet.getInt("status"));
                results.add(manufacturerModel);
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
    public void save(ManufacturerModel model) {
        try {
            String sql = "";
            if (model.getId() == 0) {
                sql = "INSERT INTO Manufacturer (name, website, status) VALUES (?, ?, ?)";
            } else {
                sql = "UPDATE Manufacturer SET name = ?, website = ?, status = ? WHERE id = ?";
            }
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getWebsite());
            statement.setInt(3, model.getStatus());                   
            if (model.getId() != 0) {
                statement.setInt(4, model.getId());
            }
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public ManufacturerModel findById(int id) {
        ManufacturerModel manufacturerModel = null;
        String sql = "SELECT * FROM Manufacturer WHERE id = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                manufacturerModel = new ManufacturerModel();
                manufacturerModel.setId(resultSet.getInt("id"));
                manufacturerModel.setName(resultSet.getString("name"));
                manufacturerModel.setWebsite(resultSet.getString("website"));
                manufacturerModel.setStatus(resultSet.getInt("status"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturerModel;
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM Manufacturer where id = ?";
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
