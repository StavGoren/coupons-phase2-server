package com.stav.server.logic;

import com.stav.server.beans.Purchase;
import com.stav.server.dal.PurchasesDal;
import com.stav.server.dto.PurchaseCustomerDto;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;

import java.util.List;

public class PurchaseLogic {
    private PurchasesDal purchasesDal;

    public PurchaseLogic() {
        this.purchasesDal = new PurchasesDal();
    }

    /**********************
     * Public methods
     **********************/

    public int createPurchase(Purchase purchase) throws ServerException {
        ValidationsUtils.validateNumber(purchase.getAmount());
        int id = purchasesDal.createPurchase(purchase);
        return id;
    }

    public void removePurchaseById(int id, UserType userType) throws ServerException {
        ValidationsUtils.validateNumber(id);
        isIdExist(id);
        ValidationsUtils.validateUserType(userType);
        purchasesDal.removePurchase(id);
    }


    public Purchase getPurchaseByPurchaseId(int id) throws ServerException {
        ValidationsUtils.validateNumber(id);
        isIdExist(id);
        return purchasesDal.getPurchaseById(id);
    }

    public List<Purchase> getPurchasesByPage(int pageNumber) throws ServerException {
        ValidationsUtils.validateNumber(pageNumber);
        return purchasesDal.getPurchasesByPage(pageNumber);
    }

    public List<PurchaseCustomerDto> getPurchasesAndCustomersByPage(int pageNumber) throws ServerException {
        ValidationsUtils.validateNumber(pageNumber);
        return purchasesDal.getPurchasesAndCustomersByPage(pageNumber);
    }

    /*********************
     * Validation
     **********************/


    private boolean isIdExist(int id) throws ServerException{
        List<Purchase> purchaseList = purchasesDal.getAllPurchases();
        for (int i = 0; i < purchaseList.size(); i++) {
            if(purchaseList.get(i).getId() == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " there is no purchase with the ID number " + id);
    }
}
