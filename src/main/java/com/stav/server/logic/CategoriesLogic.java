package com.stav.server.logic;

import com.stav.server.beans.Category;
import com.stav.server.dal.CategoriesDal;
import com.stav.server.exceptions.ServerException;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.utils.ValidationsUtils;

import java.util.List;

public class CategoriesLogic {
    private CategoriesDal categoriesDal;

    public CategoriesLogic() {
        this.categoriesDal = new CategoriesDal();
    }


    /*******************************
     Public methods and actions
     *******************************/

    public int createCategory(Category category) throws ServerException {
        validateCategory(category.getName());
        return categoriesDal.createCategory(category);
    }


    public void updateCategory(String name, int id) throws ServerException {
        validateCategory(name);
        isValidId(id);
        categoriesDal.updateCategory(name, id);
    }


    public Category getCategory(int id) throws ServerException {
        isValidId(id);
        return categoriesDal.getCategory(id);
    }

    public List<Category> getCategoriesByPage(int pageNumber) throws Exception { //Replace with Server Exception
        ValidationsUtils.validateNumber(pageNumber);
        return categoriesDal.getCategoriesByPage(pageNumber);
    }

    public void removeCategory(int id, UserType userType) throws ServerException {
        isValidId(id);
        ValidationsUtils.validateUserType(userType);
        categoriesDal.removeCategory(id);
    }


    /*******************************
     ValidationsUtils
     *******************************/

    private boolean isValidId(int id) throws ServerException {
        ValidationsUtils.validateNumber(id);

        List<Category> categoriesList = categoriesDal.getAllCategories();
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getId() == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " id " + id + " does not exist in our data base");
    }

    private void validateCategory(String name) throws ServerException {

        ValidationsUtils.validateNameLength(name);

//        Check if category exists in DB
        List<Category> categoryList = categoriesDal.getAllCategories();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getName().equals(name)) {
                throw new ServerException(ErrorType.NAME_EXISTS, " category " + name + " is already in our data base");
            }
        }
    }
}
