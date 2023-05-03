package com.stav.server.dal;

import com.stav.server.beans.Category;
import com.stav.server.consts.Constants;
import com.stav.server.exceptions.ServerException;
import com.stav.server.enums.ErrorType;
import com.stav.server.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDal {
    public int createCategory(Category category) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT INTO categories (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new ServerException(ErrorType.GENERAL_ERROR, " invalid category key during generation");
            }
            return resultSet.getInt(1);

        } catch (SQLException e){
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to create category " + category.toString(), e);
        }finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCategory(String name, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE categories SET name=?  WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update category name" + name, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Category> getAllCategories() throws ServerException {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name FROM categories";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Category category = getCategoryFromResultSet(result);
                categories.add(category);
            }
            return categories;

        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve categories", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public List<Category> getCategoriesByPage(int pageNumber) throws ServerException {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name FROM categories LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber-1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Category category = getCategoryFromResultSet(result);
                categories.add(category);
            }
            return categories;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve categories", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public Category getCategory(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        Category category = new Category();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name FROM categories WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                category = getCategoryFromResultSet(result);
            }
            return category;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve category " + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void removeCategory(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE from categories WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove category " + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
