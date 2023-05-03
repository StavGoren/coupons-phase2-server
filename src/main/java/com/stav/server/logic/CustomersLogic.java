//package com.stav.server.logic;
//
//import com.stav.server.beans.Customer;
//import com.stav.server.dal.CustomersDal;
//import com.stav.server.enums.ErrorType;
//import com.stav.server.enums.UserType;
//import com.stav.server.exceptions.ServerException;
//import com.stav.server.utils.ValidationsUtils;
//
//import java.util.List;
//
//public class CustomersLogic {
//    private CustomersDal customersDal;
//    private UsersLogic usersLogic;
//
//    public CustomersLogic() {
//        this.customersDal = new CustomersDal();
//        this.usersLogic = new UsersLogic();
//    }
//
//
//    public void createCustomer(Customer customer) throws Exception {
//        validateCustomer(customer);
//        int id = usersLogic.createUser(customer.getUser());
//        customer.setId(id);
//        customersDal.createCustomer(customer);
//    }
//
//    public void updateCustomerFullName(String fullName, int id) throws ServerException {
//        ValidationsUtils.validateNameLength(fullName);
//        isValidId(id);
//        customersDal.updateCustomerFullName(fullName, id);
//    }
//
//    public void updatePhoneNumber(String phoneNumber, int id) throws ServerException {
//        ValidationsUtils.validatePhoneNumber(phoneNumber);
//        findPhoneNumberInDb(phoneNumber);
//        isValidId(id);
//        customersDal.updateCustomerPhoneNumber(phoneNumber, id);
//    }
//
//    public void updateAddress(String address, int id) throws ServerException {
//        ValidationsUtils.validateAddress(address);
//        isValidId(id);
//        customersDal.updateCustomerAddress(address, id);
//    }
//
//    public void removeCustomerById(int id, UserType userType) throws ServerException {
//        isValidId(id);
//        ValidationsUtils.validateUserType(userType);
//        usersLogic.removeUser(id, userType);
//    }
//
//    public Customer getCustomerById(int id) throws ServerException {
//        isValidId(id);
//        return customersDal.getCustomerById(id);
//    }
//
//    public List<Customer> getCustomersByPage(int pageNumber) throws ServerException {
//        ValidationsUtils.validateNumber(pageNumber);
//        return customersDal.getAllCustomersByPage(pageNumber);
//    }
//
//
//    /*********************
//     * ValidationsUtils
//     *********************/
//
//
//    private boolean isValidId(int id) throws ServerException {
//        ValidationsUtils.validateNumber(id);
//
//        List<Customer> customerList = customersDal.getAllCustomers();
//        for (int i = 0; i < customerList.size(); i++) {
//            if(customerList.get(i).getUser().getId() == id){
//                return true;
//            }
//        }
//        throw new ServerException(ErrorType.DATA_NOT_FOUND, " please make sure you entered the correct customer ID");
//    }
//
//
//    private void validateCustomer(Customer customer) throws ServerException {
//        ValidationsUtils.validateNameLength(customer.getFullName());
//        ValidationsUtils.validatePhoneNumber(customer.getPhoneNumber());
//        findPhoneNumberInDb(customer.getPhoneNumber());
//        ValidationsUtils.validateAddress(customer.getAddress());
//    }
//
//
//    // Check if phone number already exists
//    private void findPhoneNumberInDb(String phoneNumber) throws ServerException {
//        List<Customer> customerList = customersDal.getAllCustomers();
//        for (int i = 0; i < customerList.size(); i++) {
//            if (customerList.get(i).getPhoneNumber().equals(phoneNumber)) {
//                throw new ServerException(ErrorType.PHONE_NUMBER_EXISTS, " please make sure you entered the right number");
//            }
//        }
//    }
//}
