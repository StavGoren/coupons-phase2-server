//package com.stav.server.logic;
//
//import com.stav.server.beans.User;
//import com.stav.server.dal.CustomersDal;
//import com.stav.server.dal.UsersDal;
//import com.stav.server.enums.ErrorType;
//import com.stav.server.enums.UserType;
//import com.stav.server.exceptions.ServerException;
//import com.stav.server.utils.ValidationsUtils;
//
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class UsersLogic {
//    private UsersDal usersDal;
//    private CustomersDal customersDal;
//
//    public UsersLogic() {
//        this.usersDal = new UsersDal();
//        this.customersDal = new CustomersDal();
//    }
//
//
//    public int createUser(User user) throws Exception {
//        validateUserDetails(user);
//        int id = usersDal.createUser(user);
//        return id;
//    }
//
//
//    public void updateUserName(String userName, int id) throws ServerException {
//        validateUserName(userName);
//        isUserNameExist(userName);
//        isValidIdId(id);
//        usersDal.updateUserName(userName, id);
//    }
//
//
//    public void updatePassword(String password, int id) throws ServerException {
//        validatePassword(password);
//        isValidIdId(id);
//        usersDal.updatePassword(password, id);
//    }
//
//
//    public void removeUser(int id, UserType userType) throws ServerException {
//        isValidIdId(id);
//        ValidationsUtils.validateUserType(userType);
//        customersDal.removeCustomer(id);
//        usersDal.removeUser(id);
//    }
//
//    public User getUserByIdWithoutPassword(int id) throws ServerException {
//        isValidIdId(id);
//        return usersDal.getUserByIdWithoutPassword(id);
//    }
//
//    public String getUserPasswordById(int id, UserType userType) throws ServerException {
//        isValidIdId(id);
//        ValidationsUtils.validateUserType(userType);
//        return usersDal.getUserPassword(id);
//    }
//
//    public List<User> getUsersByPageWithoutPassword(int pageNumber) throws ServerException {
//        ValidationsUtils.validateNumber(pageNumber);
//        return usersDal.getUsersByPage(pageNumber);
//    }
//
//    // Avi's function - keep!!
//    public String login(UserEntity user) throws Exception {
//        // mocking a successfull login
//        SuccessfulLoginData userData = usersDal.login(user.getName(), user.getPassword());
//        if (userData == null) {
//            throw new Exception("Failed to login");
//        }
//
//        String token = JWTUtils.createJWT(userData);
//        return token;
//    }
//
//
//    /*********************
//     * ValidationsUtils
//     ********************/
//
//
//    private void validateUserDetails(User user) throws ServerException {
//        validateUserName(user.getUserName());
//        isUserNameExist(user.getUserName());
//        validatePassword(user.getPassword());
//    }
//
//    private void validatePassword(String password) throws ServerException {
//        if(password.length() < 8) {
//            throw new ServerException(ErrorType.INVALID_PASSWORD_LENGTH, " for your own security, make sure your password is long enough");
//        }
//        if(password.length() > 45) {
//            throw new ServerException(ErrorType.INVALID_PASSWORD_LENGTH, " please make sure your password meets the length requirements");
//        }
//    }
//
//    private static void validateUserName(String email) throws ServerException {
//        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
//        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//        if (!matcher.matches()) {
//            throw new ServerException(ErrorType.INVALID_EMAIL_PATTERN, " please check your entry");
//        }
//    }
//
//    private boolean isUserNameExist(String userName) throws ServerException{
//        List<String> userNameList = usersDal.getAllUserNames();
//        for (int i = 0; i < userNameList.size(); i++) {
//            if(userNameList.get(i).equals(userName)){
//                throw new ServerException(ErrorType.USERNAME_EXISTS, " please enter a different email");
//            }
//        }
//        return true;
//    }
//
//
//    private boolean isValidIdId(int id) throws ServerException {
//        ValidationsUtils.validateNumber(id);
//
//        List<Integer> usersIdList = usersDal.getAllUserIds();
//        for (int i = 0; i < usersIdList.size(); i++) {
//            if (usersIdList.get(i) == id) {
//                return true;
//            }
//        }
//        throw new ServerException(ErrorType.DATA_NOT_FOUND, " this user ID does not exist in our data base");
//    }
//
//}
