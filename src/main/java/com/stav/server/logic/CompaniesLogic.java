package com.stav.server.logic;

import com.stav.server.beans.Company;
import com.stav.server.dal.CompaniesDal;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;

import java.util.List;

public class CompaniesLogic {

    private CompaniesDal companiesDal;

    public CompaniesLogic() {
        this.companiesDal = new CompaniesDal();
    }


    /*******************************
     Public methods and actions
     *******************************/

    public int createCompany(Company company) throws ServerException {
        validateCompany(company);
        int id = companiesDal.createCompany(company);
        return id;
    }


    public void updateCompanyAddress(String address, int id) throws ServerException {
        ValidationsUtils.validateAddress(address);
        isValidId(id);
        companiesDal.updateCompanyAddress(address, id);
    }


    public void updateCompanyPhoneNuber(String phoneNumber, int id) throws Exception{
        ValidationsUtils.validatePhoneNumber(phoneNumber);
        validatePhoneNumber(phoneNumber);
        isValidId(id);
        companiesDal.updateCompanyPhoneNumber(phoneNumber, id);
    }


    public void updateCompanyName(String name, int id) throws Exception {
        validateCompanyName(name);
        isValidId(id);
        companiesDal.updateCompanyName(name, id);
    }


    public void removeCompanyById(int id, UserType userType) throws ServerException {
        ValidationsUtils.validateUserType(userType);
        isValidId(id);
        companiesDal.removeCompany(id);
    }


    public Company getCompanyById(int id) throws ServerException {
        isValidId(id);
        return companiesDal.getCompanyById(id);
    }


    public List<Company> getCompaniesByPage(int pageNumber) throws ServerException {
        ValidationsUtils.validateNumber(pageNumber);
        return companiesDal.getCompaniesByPage(pageNumber);
    }



    /********************
     ValidationsUtils
     *********************/

    private boolean isValidId(int id) throws ServerException {
        ValidationsUtils.validateNumber(id);

        // Checking if ID exists
        List<Company> companies = companiesDal.getAllCompanies();
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId() == id) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " failed to find a company with the given ID");
    }

    private void validateCompany(Company company) throws ServerException {
        validateCompanyName(company.getName());
        validatePhoneNumber(company.getPhoneNumber());
        ValidationsUtils.validateAddress(company.getAddress());
    }


    private void validatePhoneNumber(String phoneNumber) throws ServerException {
        ValidationsUtils.validatePhoneNumber(phoneNumber);

        // Checking if phone number already exists
        List<Company> companyList = companiesDal.getAllCompanies();
        for (int i = 0; i < companyList.size(); i++) {
            if (companyList.get(i).getPhoneNumber().equals(phoneNumber)) {
                throw new ServerException(ErrorType.PHONE_NUMBER_EXISTS, " please make sure the data is valid");
            }
        }
    }

    private void validateCompanyName(String name) throws ServerException {
        ValidationsUtils.validateNameLength(name);

        // Checking if name already exists
        List<Company> companyList = companiesDal.getAllCompanies();
        for (int i = 0; i < companyList.size(); i++) {
            if (companyList.get(i).getName().equals(name)) {
                throw new ServerException(ErrorType.NAME_EXISTS, " please find another name");
            }
        }
    }
}
