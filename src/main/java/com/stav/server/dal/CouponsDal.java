package com.stav.server.dal;


import com.stav.server.beans.Coupon;
import com.stav.server.consts.Constants;
import com.stav.server.dto.CouponCompanyDto;
import com.stav.server.exceptions.ServerException;
import com.stav.server.enums.ErrorType;
import com.stav.server.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsDal {
    public int createCoupon(Coupon coupon) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "INSERT INTO coupons (name, price_in_nis, description, start_date, end_date, category_id, company_id, coupon_number) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, coupon.getName());
            preparedStatement.setDouble(2, coupon.getPriceInNis());
            preparedStatement.setString(3, coupon.getDescription());
            preparedStatement.setDate(4, coupon.getStartDate());
            preparedStatement.setDate(5, coupon.getEndDate());
            preparedStatement.setInt(6, coupon.getCategoryId());
            preparedStatement.setInt(7, coupon.getCompanyId());
            preparedStatement.setString(8, coupon.getCouponNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
               throw new SQLException(ErrorType.GENERAL_ERROR + " failed to create coupon during generation");
            }
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to create coupon" + coupon.toString(), e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCouponDescription(String description, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE coupons SET description=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " please try again later", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCouponPrice(Double priceInNis, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE coupons SET price_in_nis=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setDouble(1, priceInNis);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update coupon price" + priceInNis, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void updateCouponName(String name, int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "UPDATE coupons SET name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to update coupon name" + name, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Coupon> getAllCoupons() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Coupon> coupons = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, price_in_nis, description, start_date, end_date, category_id, company_id, coupon_number FROM coupons";
            preparedStatement = connection.prepareStatement(sqlStatement);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Coupon coupon = getCouponFromResultSet(result);
                coupons.add(coupon);
            }
            return coupons;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve coupons", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }

    }

    public List<CouponCompanyDto> getCouponsAndCompaniesByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<CouponCompanyDto> couponCompanyDtoList = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT coup.id, coup.name, coup.price_in_nis, coup.description, coup.start_date, coup.end_date, coup.company_id," +
                    "comp.name, comp.phone_number FROM coupons coup JOIN companies comp ON coup.company_id=comp.id LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber - 1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                CouponCompanyDto couponCompanyDto = getCouponAndCompanyFromResultSet(result);
                couponCompanyDtoList.add(couponCompanyDto);
            }
            return couponCompanyDtoList;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve coupons and companies data", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public List<Coupon> getCouponsByPage(int pageNumber) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        List<Coupon> coupons = new ArrayList<>();

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, price_in_nis, description, start_date, end_date, category_id, company_id, coupon_number FROM coupons LIMIT ? OFFSET ?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            preparedStatement.setInt(2, (pageNumber - 1) * Constants.AMOUNT_OF_ITEMS_PER_PAGE);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                Coupon coupon = getCouponFromResultSet(result);
                coupons.add(coupon);
            }

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve coupons", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
        return coupons;
    }

    public Coupon getCouponById(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Coupon coupon1 = new Coupon();
        ResultSet result = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "SELECT id, name, price_in_nis, description, start_date, end_date, category_id, company_id, coupon_number FROM coupons where id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                coupon1 = getCouponFromResultSet(result);
            }
            return coupon1;

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to retrieve coupon" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public void removeExpiredCoupon() throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE FROM coupons WHERE end_date < date(now())";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove expired coupon", e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void removeCoupon(int id) throws ServerException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtils.getConnection();
            String sqlStatement = "DELETE FROM coupons WHERE id=?";
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " failed to remove coupon" + id, e);
        } finally {
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    private CouponCompanyDto getCouponAndCompanyFromResultSet(ResultSet result) throws SQLException {
        CouponCompanyDto couponCompanyDto = new CouponCompanyDto();
        couponCompanyDto.setId(result.getInt("id"));
        couponCompanyDto.setCouponName(result.getString("name"));
        couponCompanyDto.setPriceInNis(result.getDouble("price_in_nis"));
        couponCompanyDto.setDescription(result.getString("description"));
        couponCompanyDto.setStartDate(result.getDate("start_date"));
        couponCompanyDto.setEndDate(result.getDate("end_date"));
        couponCompanyDto.setCompanyId(result.getInt("company_id"));
        couponCompanyDto.setCompanyName(result.getString("name"));
        couponCompanyDto.setPhoneNumber(result.getString("phone_number"));

        return couponCompanyDto;
    }

    private Coupon getCouponFromResultSet(ResultSet result) throws SQLException {
        Coupon coupon = new Coupon();
        coupon.setId(result.getInt("id"));
        coupon.setName(result.getString("name"));
        coupon.setPriceInNis(result.getDouble("price_in_nis"));
        coupon.setDescription(result.getString("description"));
        coupon.setStartDate(result.getDate("start_date"));
        coupon.setEndDate(result.getDate("end_date"));
        coupon.setCategoryId(result.getInt("category_id"));
        coupon.setCompanyId(result.getInt("company_id"));
        coupon.setCouponNumber(result.getString("coupon_number"));

        return coupon;
    }
}
