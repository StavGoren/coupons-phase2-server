package com.stav.server.dal;

import com.stav.server.beans.User;
import com.stav.server.consts.Constants;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.valueOf;

public class UsersDal {
    public int createUser(User user) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT INTO users (user_name, password, user_type, company_id) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, valueOf(user.getUserType()));
            preparedStatement.setInt(4, user.getCompanyId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ServerException(ErrorType.GENERAL_ERROR, " invalid user key during creation");
            }
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to create new user" + user, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updatePassword(String password, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE users SET password=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update password" + password, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateUserName(String userName, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE users SET user_name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update user name", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public String getUserPassword(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        User user = new User();


        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT password FROM users WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()){
                user.setPassword(result.getString(1));
            }
            return user.getPassword();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve user password", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<String> getAllUserNames() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<String> userNameList = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT user_name FROM users";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                userNameList.add(result.getString(1));
            }
            return userNameList;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve user names", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Integer> getAllUserIds() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Integer> userIdList = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id FROM users";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                userIdList.add(result.getInt(1));
            }
            Collections.sort(userIdList);
            return userIdList;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve password", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<User> getAllUsers() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        User user = new User();
        List<User> users = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, user_name, user_type, company_id FROM users";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while(result.next()) {
                user = getUserFromResultSetNoPassword(result);
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve users", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<User> getUsersByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        User user = new User();
        List<User> userList = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, user_name, user_type, company_id FROM users LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);

            result = preparedStatement.executeQuery();
            while(result.next()) {
                user = getUserFromResultSetNoPassword(result);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve users", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public User getUserByIdWithoutPassword(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        User user = new User();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, user_name, user_type, company_id FROM users WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while(result.next()) {
                user = getUserFromResultSetNoPassword(result);
            }
            return user;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve user" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void removeUser(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE from users WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove user" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    private User getUserFromResultSetNoPassword(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String userName = result.getString("user_name");
        UserType type = UserType.valueOf(result.getString("user_type"));
        int companyId = result.getInt("company_id");
        User user = new User(id, userName, "****", type, companyId);
        return user;
    }
}