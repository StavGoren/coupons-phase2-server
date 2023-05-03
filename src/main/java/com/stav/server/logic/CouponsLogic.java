package com.stav.server.logic;


import com.stav.server.beans.Coupon;
import com.stav.server.dal.CouponsDal;
import com.stav.server.dto.CouponCompanyDto;
import com.stav.server.exceptions.ServerException;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.utils.ValidationsUtils;

import java.util.List;

public class CouponsLogic {
    private CouponsDal couponsDal;

    public CouponsLogic() {
        this.couponsDal = new CouponsDal();
    }

    /*******************************
      Public methods and actions
     ******************************/


    public int createCoupon(Coupon coupon) throws Exception {
        validateCoupon(coupon); // need to validate date, category name and company ID
        int id = couponsDal.createCoupon(coupon);
        return id;
    }


    public void updateCouponDescription(String description, int id) throws ServerException {
        validateDescriptionLength(description);
        validateId(id);
        couponsDal.updateCouponDescription(description, id);
    }


        public void updateCouponPrice(Double priceInNis, int id) throws ServerException {
        validatePriceValue(priceInNis);
        validateId(id);
        couponsDal.updateCouponPrice(priceInNis, id);
    }

    public void updateCouponName(String name, int id) throws ServerException {
        validateCouponName(name);
        validateId(id);
        couponsDal.updateCouponName(name, id);
    }

    public Coupon getCouponById(int id) throws ServerException {
        validateId(id);
        return couponsDal.getCouponById(id);
    }

    public List<Coupon> getCouponsByPage(int pageNumber) throws ServerException {
        ValidationsUtils.validateNumber(pageNumber);
        return couponsDal.getCouponsByPage(pageNumber);
    }

    public List<CouponCompanyDto> getCouponsAndCompaniesByPage(int pageNumber) throws ServerException {
        ValidationsUtils.validateNumber(pageNumber);
        return couponsDal.getCouponsAndCompaniesByPage(pageNumber);
    }

    public void removeCoupon(int id, UserType userType) throws ServerException {
        validateId(id);
        ValidationsUtils.validateUserType(userType);
        couponsDal.removeCoupon(id);
    }

    public void removeExpiredCoupon() throws ServerException {
        couponsDal.removeExpiredCoupon();
    }

    /***********************
     * Private methods
     ************************/


    private void validateCoupon(Coupon coupon) throws ServerException {
        validateCouponName(coupon.getName());
        validatePriceValue(coupon.getPriceInNis());
        validateDescriptionLength(coupon.getDescription());
        validateCouponNumberCharacters(coupon.getCouponNumber());

    }

    private boolean validateId(int id) throws ServerException {
        ValidationsUtils.validateNumber(id);

        throw new ServerException(ErrorType.DATA_NOT_FOUND, " please make sure you entered a correct id");
    }


    private void validateCouponNumberCharacters(String couponNumber) throws ServerException {
        for (int i = 0; i < couponNumber.length(); i++) {
            if(!Character.isLetterOrDigit(couponNumber.charAt(i))) {
                throw new ServerException(ErrorType.INVALID_COUPON_CHARACTERS, " please check you entered valid characters");
            }
        }
    }

    private void validateDescriptionLength(String description) throws ServerException {
        if(description.length() < 10){
            throw new ServerException(ErrorType.INVALID_DESCRIPTION_LENGTH, " please make sure there is enough information");
        }
        if(description.length() > 250){
            throw new ServerException(ErrorType.INVALID_DESCRIPTION_LENGTH, " please keep the information elaborated and concise");
        }
    }

    private void validatePriceValue(Double price) throws ServerException {
        if(price < 1) {
            throw new ServerException(ErrorType.INVALID_PRICE, " please make sure the value is 1 NIS or greater");
        }
    }

    private void validateCouponName(String name) throws ServerException {
        ValidationsUtils.validateNameLength(name);

        List<Coupon> couponList = couponsDal.getAllCoupons();
        for (int i = 0; i < couponList.size(); i++) {
            if(couponList.get(i).getName().equals(name)){
                throw new ServerException(ErrorType.NAME_EXISTS, " please find another name");
            }
        }
    }
}