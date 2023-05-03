package com.stav.server.dal;

import com.stav.server.beans.Company;
import com.stav.server.consts.Constants;
import com.stav.server.enums.ErrorType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompaniesDal {
    public int createCompany(Company company) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT INTO companies (name, phone_number, address) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getPhoneNumber());
            preparedStatement.setString(3, company.getAddress());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new ServerException(ErrorType.GENERAL_ERROR, " invalid key during generation");
            }
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to create company" + company.toString(), e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCompanyAddress(String address, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE companies SET address=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update address for company" + address, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCompanyName(String name, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE companies SET name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update company name" + name, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCompanyPhoneNumber(String phoneNumber, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE companies SET phone_number=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update phone number for company" + phoneNumber, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Company> getAllCompanies() throws ServerException {
        List<Company> companies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, phone_number, address FROM companies";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Company company = getCompanyFromResultSet(result);
                companies.add(company);
            }
            return companies;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to to retrieve companies", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<Company> getCompaniesByPage(int pageNumber) throws ServerException {
        List<Company> companies = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, phone_number, address FROM companies LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Company company = getCompanyFromResultSet(result);
                companies.add(company);
            }
            return companies;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to to retrieve companies", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Company getCompanyById(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Company company = new Company();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, phone_number, address FROM companies WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while(result.next()) {
                company = getCompanyFromResultSet(result);
            }
            return company;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to to retrieve company" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void removeCompany(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE from companies WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove company" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setPhoneNumber(resultSet.getString("phone_number"));
        company.setAddress(resultSet.getString("address"));
        return company;
    }
}

