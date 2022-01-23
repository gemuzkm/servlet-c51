package validator;

import entity.User;
import storage.UserStorageInMemory;

public class UserValidation {
    UserStorageInMemory userStorageInMemory = new UserStorageInMemory();

    public boolean existsUser(User user) {
        if (userStorageInMemory.getByUserLogin(user.getLogin()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existsUser(String userLogin) {
        if (userStorageInMemory.getByUserLogin(userLogin) != null) {
            return true;
        } else {
            return false;
        }
    }

    public User validUserPassword(String userLogin, String userPassword) {
        User user = userStorageInMemory.getByUserLogin(userLogin);
        if (user.getPassword().equals(userPassword)) {
            return user;
        }
        return null;
    }
}
