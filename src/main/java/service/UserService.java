package service;

import entity.User;
import dao.InMemory.UserStorageInMemory;
import dao.JDBC.UserStorageJDBC;
import web.validator.UserValidator;

import java.util.List;

public class UserService {
    private UserStorageJDBC userStorageJDBC = UserStorageJDBC.getInstance();
    private CalculatorService calculatorService = CalculatorService.getInstance();
    private UserValidator userValidator = new UserValidator();


    public User getByUserLogin(String userLogin) {
        return userStorageJDBC.getUserByLogin(userLogin);
    }

    public void deleteHistoryAfterIdChange(String userLogin, String sessionID) {
        if(userValidator.changedUserSessionID(userLogin, sessionID)) {
            calculatorService.delHistory(userLogin);
        }
    }

    public boolean changedUserSessionID(String userLogin, String sessionID) {
        return userValidator.changedUserSessionID(userLogin, sessionID);
    }

    public void addUser(User user) {
        userStorageJDBC.addUser(user);
    }

    public void delUser(User user) {
        userStorageJDBC.delUser(user);
    }

    public List<User> getListUser() {
        return userStorageJDBC.getListUser();
    }
}
