/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.UserModel;

/**
 *
 * @author Admin
 */
public interface IUserService {
    UserModel authenticatedUser(String userName, String password);
    void save(UserModel newUser);
    UserModel findById(int id);
    void update(UserModel updateUser);
}
