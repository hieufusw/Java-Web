package dao.impl;

import dao.IUserDAO;
import model.RoleModel;
import model.UserModel;
import util.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    @Override
    public UserModel findByUserNameAndPassword(String name, String password) {
        UserModel user = null;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? AND status = 1 AND isdelete = 0";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setFullName(resultSet.getString("fullname"));
                user.setRoleId(resultSet.getInt("roleid"));
                user.setDeleted(resultSet.getInt("isdelete"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public RoleModel findRoleById(int id) {
        RoleModel role = null;
        String sql = "SELECT * FROM Role WHERE id = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = new RoleModel();
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public RoleModel findRoleByRole(String code) {
        RoleModel role = null;
        String sql = "SELECT * FROM Role WHERE code = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = new RoleModel();
                role.setId(resultSet.getInt("id"));
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void save(UserModel newUser) {
        try {
            String sql = "";
            if (newUser.getId() == 0) {
                sql = "INSERT INTO Users (username, password, status, address, phone, fullname, roleid, isdelete) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Users SET username = ?, password = ?, status = ?, address = ?, phone = ?, fullname = ?, roleid = ?, isdelete = ? WHERE id = ?";
            }
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newUser.getUserName());
            statement.setString(2, newUser.getPassword());
            statement.setInt(3, newUser.getStatus());
            statement.setString(4, newUser.getAddress());
            statement.setString(5, newUser.getPhone());
            statement.setString(6, newUser.getFullName());
            statement.setInt(7, newUser.getRoleId());
            statement.setInt(8, newUser.getDeleted());
            if (newUser.getId() != 0) {
                statement.setInt(9, newUser.getId());
            }
            statement.executeUpdate();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findById(int id) {
        UserModel user = null;
        String sql = "SELECT * FROM Users WHERE id = ?";
        try {
            Connection connection = ConnectionUtils.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setFullName(resultSet.getString("fullname"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setRoleId(resultSet.getInt("roleid"));
                user.setStatus(resultSet.getInt("status"));
                user.setPassword(resultSet.getString("password"));
                user.setDeleted(resultSet.getInt("isdelete"));
            }
            resultSet.close();
            statement.close();
            ConnectionUtils.disconnect(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserModel> findByRole(String role) {
        List<UserModel> results = new ArrayList<>();
        String sql = "SELECT * FROM Users AS u INNER JOIN Role AS r ON r.id = u.roleid WHERE r.code = '"+role+"'";
        sql += " AND u.isdelete = 0 ";
        try {
            Connection connection = ConnectionUtils.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setFullName(resultSet.getString("fullname"));
                user.setAddress(resultSet.getString("address"));
                user.setPhone(resultSet.getString("phone"));
                user.setRoleId(resultSet.getInt("roleid"));
                user.setStatus(resultSet.getInt("status"));
                user.setPassword(resultSet.getString("password"));
                user.setDeleted(resultSet.getInt("isdelete"));
                results.add(user);
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
    public void delete(int id) {
        try {
            String sql = "DELETE FROM Users where id = ?";
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
