package com.app.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.domain.exception.IncorrectUserInfoException;
import com.app.domain.model.UserDomain;

public class UserValidation {

    private static Logger LOGGER = LoggerFactory.getLogger(UserValidation.class.getName());
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@?¡¿!#*+$%]).{8,20})";
    private static final String NAME_PATTERN = "[a-zA-Z]+";
    private static final Integer CODE_LENGHT = 1000000000;

    public static void userValidateAdd(UserDomain user) throws IncorrectUserInfoException {

        if (user.getUserCode() == null || user.getUserEmail() == null || user.getUserName() == null
            || user.getUserPass() == null) {
            LOGGER.error("Some requeried fields are missing");
            throw new IncorrectUserInfoException("null value");
        }

        if (!(user.getUserCode() <= CODE_LENGHT)) {
            LOGGER.error("User code is incorrect: " + user.getUserCode());
            throw new IncorrectUserInfoException("code lenght is too long");
        }

        if ((user.getUserEmail().indexOf("@") < 1)) {
            LOGGER.error("User email is not valid: " + user.getUserEmail());
            throw new IncorrectUserInfoException("invalid email address");
        }

        if (!(user.getUserName().matches(NAME_PATTERN))) {
            LOGGER.error("User name is not valid: " + user.getUserName());
            throw new IncorrectUserInfoException("invalid name format");
        }

        if (!user.getUserPass().matches(PASSWORD_PATTERN)) {
            LOGGER.error("User password is not valid: " + user.getUserPass());
            throw new IncorrectUserInfoException("invalid password format");
        }

    }

    public static void userValidateCode(String code) throws IncorrectUserInfoException {

        if (code == null) {
            LOGGER.error("User email value is null");
            throw new IncorrectUserInfoException("code null value");
        }
        if (code.indexOf("@") < 1) {
            LOGGER.error("User email format is not valid");
            throw new IncorrectUserInfoException("invalid email value");
        }

    }

    public static void userValidateCode(Integer code) throws IncorrectUserInfoException {

        if (code == null) {
            LOGGER.error("User code value is null");
            throw new IncorrectUserInfoException("null value");
        }

        if (code >= CODE_LENGHT || code.equals(0)) {
            LOGGER.error("User code value is invalid");
            throw new IncorrectUserInfoException("invalid code value");
        }
    }
}
