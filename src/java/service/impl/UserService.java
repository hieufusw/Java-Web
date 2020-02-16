package service.impl;

import dao.IUserDAO;
import model.RoleModel;
import model.UserModel;
import service.IUserService;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;

public class UserService implements IUserService {
    
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel authenticatedUser(String userName, String password) {
        UserModel userModel = userDAO.findByUserNameAndPassword(userName, password);
        if (userModel != null) {
            userModel.setRoleCode(userDAO.findRoleById(userModel.getRoleId()).getCode());
        }
        return userModel;
    }

    @Override
    public void save(UserModel newUser) {
        newUser.setStatus(1);
        newUser.setDeleted(0);
        RoleModel role = userDAO.findRoleByRole("user");
        newUser.setRoleId(role.getId());
        userDAO.save(newUser);
    }

    @Override
    public UserModel findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void update(UserModel updateUser) {
        UserModel existedUser = userDAO.findById(updateUser.getId());
        updateUser.setStatus(existedUser.getStatus());
        updateUser.setRoleId(existedUser.getRoleId());
        updateUser.setDeleted(existedUser.getDeleted());
        userDAO.save(updateUser);
    }
    
}
