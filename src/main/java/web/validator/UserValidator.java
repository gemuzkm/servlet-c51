package web.validator;

import entity.User;
import dao.InMemory.UserStorageInMemory;
import dao.JDBC.UserStorageJDBC;

public class UserValidator {
    private UserStorageInMemory userStorageInMemory = UserStorageInMemory.getInstance();
    private UserStorageJDBC userStorageJDBC = UserStorageJDBC.getInstance();

    public boolean existsUser(User user) {
        if (userStorageJDBC.getUserByLogin(user.getLogin()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existsUser(String userLogin) {
        if (userStorageJDBC.getUserByLogin(userLogin) != null) {
            return true;
        } else {
            return false;
        }
    }

    public User validUserPassword(String userLogin, String userPassword) {
        User user = userStorageJDBC.getUserByLogin(userLogin);
        if (user.getPassword().equals(userPassword)) {
            return user;
        }
        return null;
    }

    public boolean changedUserSessionID(String userLogin, String sessionID) {
        User user = userStorageJDBC.getUserByLogin(userLogin);

        if (user.getSessionID().equals(sessionID)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validUserRole(int userRole) {
        if (userRole == 1 || userRole == 2) {
            return true;
        } else {
            return false;
        }
    }
}
