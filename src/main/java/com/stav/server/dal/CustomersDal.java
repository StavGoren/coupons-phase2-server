package com.stav.server.dal;

import com.stav.server.beans.Customer;
import com.stav.server.beans.User;
import com.stav.server.consts.Constants;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDal {
    public int createCustomer(Customer customer) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT into customers (user_id, full_name, phone_number, address) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, customer.getUser().getId());
            preparedStatement.setString(2, customer.getFullName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.executeUpdate();

            return customer.getUser().getId();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to insert customer details" + customer.toString(), e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCustomerPhoneNumber(String phoneNumber, int userId) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE customers SET phone_number=? WHERE user_id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update phone number", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCustomerAddress(String address, int userId) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE customers SET address=? WHERE user_id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update address", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCustomerFullName(String fullName, int userId) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE customers SET full_name=? WHERE user_id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update customer name", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<Customer> getAllCustomersByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Customer> customers = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT user_id, full_name, phone_number, address FROM customers LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                User user = new User(-1, "", "", UserType.Customer, -1);
                Customer customer = new Customer(user, "", "", "");
                customer.setId(result.getInt(1));
                customer.setFullName(result.getString(2));
                customer.setPhoneNumber(result.getString(3));
                customer.setAddress(result.getString(4));
                customers.add(customer);
            }
            return customers;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve customers"  + customers.toString(), e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Customer> getAllCustomers() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Customer> customers = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT user_id, full_name, phone_number, address FROM customers";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                User user = new User(-1, "", "", UserType.Customer, -1);
                Customer customer = new Customer(user, "", "", "");
                customer.setId(result.getInt(1));
                customer.setFullName(result.getString(2));
                customer.setPhoneNumber(result.getString(3));
                customer.setAddress(result.getString(4));

                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve customers", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Customer getCustomerById(int userId) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
//        UserType userType = UserType.valueOf("");
        User user = new User(-1, "", "", UserType.Customer, -1);
        Customer customer = new Customer(user, "", "", "");
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT user_id, full_name, phone_number, address FROM customers WHERE user_id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, userId);
            result = preparedStatement.executeQuery();

            while(result.next()){
                customer.setId(userId);
                customer.setFullName(result.getString(1));
                customer.setPhoneNumber(result.getString(2));
                customer.setAddress(result.getString(3));
            }
            return customer;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve customer data" + userId, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void removeCustomer(int userId) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE FROM customers WHERE user_id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove customer" + userId, e);
        }
        finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

//    public Customer getCustomerFromResult(ResultSet resultSet) throws SQLException {
//        Customer customer = new Customer();
//        customer.setId(resultSet.getInt("user_id"));
//        customer.setFullName((resultSet.getString("full_name")));
//        customer.setPhoneNumber(resultSet.getString("phone_number"));
//        customer.setAddress(resultSet.getString("address"));
//        return customer;
//    }

}
