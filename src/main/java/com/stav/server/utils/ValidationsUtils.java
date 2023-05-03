package com.stav.server.utils;

import com.stav.server.exceptions.ServerException;
import com.stav.server.enums.ErrorType;
import com.stav.server.enums.UserType;

public class ValidationsUtils {


    public static void validateNumber(int number) throws ServerException {
        if(number < 1) {
            throw new ServerException(ErrorType.INVALID_NUMBER, " please make a valid entry");
        }
    }

    public static void validateNameLength(String name) throws ServerException {
        if (name == null) {
            throw new ServerException(ErrorType.GENERAL_ERROR, " category name can't be null");
        }
//        if (name.isBlank()) {
//            throw new ServerException(ErrorType.INVALID_NAME_LENGTH, " please make an entry");
//        }
        if (name.length() > 40) {
            throw new ServerException(ErrorType.INVALID_NAME_LENGTH, " please check the length");
        }
    }

    public static void validateAddress(String address) throws ServerException {
        if(address == null) {
            throw new ServerException(ErrorType.EMPTY_FIELD, " please enter an address");
        }
//        if(address.isBlank()) {
//            throw new ServerException(ErrorType.EMPTY_FIELD, " please make an entry with at lest 3 characters");
//        }
        if (address.length() < 2) {
            throw new ServerException(ErrorType.EMPTY_FIELD, " please make an entry with at lest 2 characters");
        }
    }

    public static void validateUserType(UserType userType) throws ServerException {
        if(userType != UserType.Admin) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE, " please turn to your supervisor or system administrator");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) throws ServerException {
        if(phoneNumber == null){
            throw new ServerException(ErrorType.EMPTY_FIELD, " this filed must contain a phone number");
        }
//        if(phoneNumber.isBlank()){
//            throw new ServerException(ErrorType.EMPTY_FIELD, " this filed must contain a phone number");
//        }
        if (phoneNumber.length() < 10) {
            throw new ServerException(ErrorType.INVALID_PHONE_NUMBER_LENGTH, " please make sure you entered a valid phone number");
        }
        if(!phoneNumber.contains("-")) {
            throw new ServerException(ErrorType.INVALID_CHARACTER_IN_PHONE_NUMBER, " please enter all the valid characters");
        }
    }
}
