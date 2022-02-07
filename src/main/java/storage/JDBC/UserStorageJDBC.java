package storage.JDBC;

import com.mysql.jdbc.Driver;
import entity.User;
import storage.Constants;
import storage.InMemory.UserStorageInMemory;
import storage.UserStorage;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class UserStorageJDBC implements UserStorage {

    private static UserStorageJDBC instance;

    private UserStorageJDBC() {
    }

    public static UserStorageJDBC getInstance() {
        if (instance == null) {
            instance = new UserStorageJDBC();
        }
        return instance;
    }

//    private String url = "jdbc:mysql://193.122.63.237:3306/servlet-c51?useUnicode=true&serverTimezone=UTC";
//    private String userName = "servlet-c51_usr";
//    private String password = "KEvUShZilD74CaBG";

    @Override
    public void addUser(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.JDBC_USER_NAME, Constants.JDBC_PASSWORD)) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users (user_name, user_login, user_password) VALUES (?,?,?)");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.execute();
            }
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    public User getByUserLogin(String userLogin) {
            try {
                Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.JDBC_USER_NAME, Constants.JDBC_PASSWORD)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "SELECT * from users where user_login = ?");
                    preparedStatement.setString(1, userLogin);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String name = resultSet.getString(1);
                        String login = resultSet.getString(2);
                        String password =  resultSet.getString(3);
                        String sessionId = resultSet.getString(4);
                        int role = resultSet.getInt(5);
                        return new User(name, login, password, sessionId, role);
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public void delUser(User user) {

    }

    @Override
    public void delUser(String userLogin) {

    }

    @Override
    public ArrayList<User> getListUser() {
        return null;
    }
}