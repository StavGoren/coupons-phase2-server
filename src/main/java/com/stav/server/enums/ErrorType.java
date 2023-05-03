package com.stav.server.enums;

public enum ErrorType {
	GENERAL_ERROR(600, "Something went wrong", true),
	INVALID_EMAIL_PATTERN(601, "Make sure you entered a correct email pattern", false),
    CONTAINS_WHITE_SPACE(602, "This field can't contain whitespace", false),
    USERNAME_EXISTS (603, "This username already exists", false),
    INVALID_USERNAME_LENGTH (604, "Username may contain 6-20 characters", false),
	INVALID_PASSWORD_LENGTH (605, "Password may contain 8-45 characters", false),
    NAME_EXISTS (606, "Name already exists", false),
    INVALID_CHARACTER_IN_PHONE_NUMBER(607, "Phone number may only contain digits and '-'", false),
    INVALID_DESCRIPTION_LENGTH (608, "Description may contain 15-250 characters", false),
    EXPIRED_COUPON (609, "This coupon has expired", false),
    INVALID_COUPON_CHARACTERS(610, "Coupon number can only contain numbers and uppercase letters", false),
    DATA_NOT_FOUND(611, "Data not found", false),
    EMPTY_FIELD(612, "This field can't be empty, false"),
    INVALID_NUMBER(613, "Number can be 1 or greater", false),
    PHONE_NUMBER_EXISTS(614, "This phone number is already in use", false),
    INVALID_NAME_LENGTH(615, "Name may contain 1-40 characters", false),
    INVALID_PHONE_NUMBER_LENGTH(616, "Phone number may contain at least 9 digits including area code", false),
    INVALID_COUPON_LENGTH(617, "This field may contain 3-30 characters", false),
    INVALID_PRICE(618, "Price must be at least 1 NIS", false),
    INVALID_USER_TYPE(619, "Only admins can perform this action", false);



    private int internalErrorNumber;
    private String errorMessage;
    private boolean showStackTrace;

    ErrorType(int internalErrorNumber, String errorMessage, boolean showStackTrace){
        this(internalErrorNumber, errorMessage);
        this.showStackTrace = showStackTrace;
    }

    ErrorType(int internalErrorNumber, String errorMessage){
        this.internalErrorNumber = internalErrorNumber;
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isShowStackTrace() {
        return showStackTrace;
    }


}
