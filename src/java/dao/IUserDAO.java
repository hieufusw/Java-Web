/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.RoleModel;
import model.UserModel;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IUserDAO {
    UserModel findByUserNameAndPassword(String name, String password); 
    RoleModel findRoleById(int id);
    RoleModel findRoleByRole(String role);
    void save(UserModel newUser);
    UserModel findById(int id);
    List<UserModel> findByRole(String role);
    void delete(int id);
}
