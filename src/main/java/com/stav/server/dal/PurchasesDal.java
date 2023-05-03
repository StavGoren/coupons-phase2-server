package com.stav.server.dal;

import com.stav.server.beans.Customer;
import com.stav.server.beans.Purchase;
import com.stav.server.beans.User;
import com.stav.server.consts.Constants;
import com.stav.server.dto.PurchaseCustomerDto;
import com.stav.server.enums.ErrorType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchasesDal {
    public int createPurchase(Purchase purchase) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT INTO purchases (customer_id, coupon_id, amount, date) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, purchase.getCustomerId());
            preparedStatement.setInt(2, purchase.getCouponId());
            preparedStatement.setInt(3, purchase.getAmount());
            preparedStatement.setDate(4, purchase.getDate());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException(ErrorType.DATA_NOT_FOUND + " invalid key during generation");
            }
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to add purchase data" + purchase.toString(), e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

//    public Purchase getPurchasesByDate(Date date) throws Exception {}

//    public Purchase updatePurchase(Purchase purchase, int id) throws Exception {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            connection = JdbcUtils.getConnection();
//            String sqlStatement = "UPDATE purchases SET customer_id=?, coupon_id=?, amount=? WHERE id=?";
//            preparedStatement = connection.prepareStatement(sqlStatement);
//            preparedStatement.setInt(1, purchase.getCustomerId());
//            preparedStatement.setInt(2, purchase.getCouponId());
//            preparedStatement.setInt(3, purchase.getAmount());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new SQLException(e);
//        } finally {
//            JdbcUtils.closeResources(connection, preparedStatement);
//        }
//        return purchase;
//    }

    public List<PurchaseCustomerDto> getPurchasesAndCustomersByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<PurchaseCustomerDto> purchaseCustomerDtoList = new ArrayList<>();
        Date date = new Date(0);

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT purchases.id, purchases.amount, purchases.date, customers.user_id, customers.full_name, customers.phone_number, customers.address " +
                    "FROM purchases JOIN customers ON purchases.customer_id=customers.user_id LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while(result.next()) {
                PurchaseCustomerDto purchaseCustomerDto = getPurchaseAndCustomerFromResultSet(result);
                purchaseCustomerDtoList.add(purchaseCustomerDto);
            }
            return purchaseCustomerDtoList;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve purchases and customers data" + pageNumber, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<Purchase> getAllPurchases() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Purchase> purchases = new ArrayList<>();
        Purchase purchase;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, customer_id, coupon_id, amount, date FROM purchases";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                purchase = getPurchaseFromResultSet(result);
                purchases.add(purchase);
            }
            return purchases;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve purchases", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Purchase> getPurchasesByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Purchase> purchases = new ArrayList<>();
        Purchase purchase;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, customer_id, coupon_id, amount, date FROM purchases LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);

            result = preparedStatement.executeQuery();

            while (result.next()) {
                purchase = getPurchaseFromResultSet(result);
                purchases.add(purchase);
            }
            return purchases;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve purchases data" + pageNumber, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Purchase getPurchaseById(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Purchase purchase = new Purchase();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, customer_id, coupon_id, amount, date FROM purchases WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                purchase = getPurchaseFromResultSet(result);
            }
            return purchase;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve purchase data" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void removePurchase(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE FROM purchases WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove purchase data" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    private PurchaseCustomerDto getPurchaseAndCustomerFromResultSet(ResultSet resultSet) throws SQLException {

        User user = new User();
        Customer customer = new Customer();
        Date date = new Date(0);
        PurchaseCustomerDto purchaseCustomerDto = new PurchaseCustomerDto(-1, 0, date, user.getId(), customer.getFullName(),
                customer.getPhoneNumber(), customer.getAddress());

        purchaseCustomerDto.setId(resultSet.getInt(1));
        purchaseCustomerDto.setAmount(resultSet.getInt(2));
        purchaseCustomerDto.setDate(resultSet.getDate(3));
        purchaseCustomerDto.setUserId(resultSet.getInt(4));
        purchaseCustomerDto.setFullName(resultSet.getString(5));
        purchaseCustomerDto.setPhoneNumber(resultSet.getString(6));
        purchaseCustomerDto.setAddress(resultSet.getString(7));

        return purchaseCustomerDto;
    }

    private Purchase getPurchaseFromResultSet(ResultSet resultSet) throws SQLException {
        Date date = new Date(0);
//        Purchase purchase = new Purchase(-1, -1, 0, date);
        Purchase purchase = new Purchase();
        purchase.setId(resultSet.getInt(1));
        purchase.setCustomerId(resultSet.getInt(2));
        purchase.setCouponId(resultSet.getInt(3));
        purchase.setAmount(resultSet.getInt(4));
        purchase.setDate(resultSet.getDate(5));

        return purchase;
    }
}
