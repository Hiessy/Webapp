package com.app.domain.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.domain.exception.DataBaseException;
import com.app.domain.exception.IncorrectUserInfoException;
import com.app.domain.model.UserDomain;
import com.app.domain.utils.UserTransformer;
import com.app.domain.utils.UserValidation;
import com.app.persistence.sql.UserDAO;
import com.app.persistence.sql.common.UserEntity;


public class UserManager {

    private static Logger LOGGER = LoggerFactory.getLogger(UserManager.class.getName());
    
    private UserDomain userDomain;
    private UserDAO userDAO;
    private UserEntity userEntity;

    public UserManager() {
        this.userDAO = new UserDAO();
    }

    public UserManager(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDomain addUser(UserDomain user) throws IncorrectUserInfoException, DataBaseException {

        UserValidation.userValidateAdd(user);

        LOGGER.info("User information is valid");
        this.userEntity = UserTransformer.toPersistenceObject(user);
        user.setUserId(this.userDAO.save(this.userEntity));

        if (user.getUserId() == null) {
            LOGGER.error("User was not added to the database: ");
            throw new DataBaseException();
        }
        return user;
    }

    public void delete(Integer code) throws IncorrectUserInfoException, HibernateException {
        try {

            UserValidation.userValidateCode(code);

            LOGGER.info("Selecting information for code: " + code);
            this.userDAO.delete(code);

        } catch (NumberFormatException e) {

            LOGGER.error("Incorrect User data: " + code);
            throw new IncorrectUserInfoException("Invalid user information: " + e);

        } catch (HibernateException e) {

            LOGGER.error("Database is broken");
            throw new HibernateException("Database is broken: " + e);
        }
    }

    public void delete(String code) throws IncorrectUserInfoException, HibernateException {
        try {
            UserValidation.userValidateCode(code);

            LOGGER.info("Selecting information for email: " + code);
            this.userDAO.delete(code);

        } catch (NumberFormatException e) {

            LOGGER.error("Incorrect User data: " + code);
            throw new IncorrectUserInfoException("Invalid user information: " + e);

        } catch (HibernateException e) {

            LOGGER.error("Database is broken");
            throw new HibernateException("Database is broken: " + e);
        }
    }

    public UserDomain select(String email) throws IncorrectUserInfoException, DataBaseException {
        try {

            UserValidation.userValidateCode(email);

            LOGGER.info("Selecting information for email: " + email);
            this.userEntity = this.userDAO.select(email);
            if (this.userEntity == null) {
                LOGGER.error("User not found in the database: " + email);
                throw new DataBaseException();
            }
            return UserTransformer.toUserObject(this.userDAO.select(email));

        } catch (IncorrectUserInfoException e) {
            LOGGER.error("Incorrect User data: " + email);
            throw new IncorrectUserInfoException("Invalid user information: " + e);
        }
    }

    public UserDomain select(Integer code) throws IncorrectUserInfoException, DataBaseException {
        try {

            UserValidation.userValidateCode(code);

            LOGGER.info("Selecting information for code: " + code);
            this.userEntity = this.userDAO.select(code);
            if (this.userEntity == null) {
                LOGGER.error("User not found in the database: " + code);
                throw new DataBaseException();
            }
            this.userDomain = UserTransformer.toUserObject(this.userEntity);
            return this.userDomain;
        } catch (NumberFormatException e) {

            LOGGER.error("Incorrect User data: " + code);
            throw new IncorrectUserInfoException("Invalid user information: " + e);
        }
    }

    public UserDomain update(String email, UserDomain user) throws IncorrectUserInfoException, DataBaseException {
        this.userEntity = UserTransformer.toPersistenceObject(user);

        this.userEntity = this.userDAO.update(email, this.userEntity);
        if (this.userEntity == null) {
            LOGGER.error("User not found in the database: " + email);
            throw new DataBaseException();
        }
        return UserTransformer.toUserObject(this.userEntity);
    }

    public UserDomain update(Integer code, UserDomain user) throws IncorrectUserInfoException, DataBaseException {
        try {

            this.userEntity = UserTransformer.toPersistenceObject(user);

            this.userEntity = this.userDAO.update(code, this.userEntity);
            if (this.userEntity == null) {
                LOGGER.error("User not found in the database: " + code);
                throw new DataBaseException();
            }
            this.userDomain = UserTransformer.toUserObject(this.userEntity);
            return this.userDomain;
        } catch (NumberFormatException e) {
            LOGGER.error("Incorrect User data: " + code);
            throw new IncorrectUserInfoException("Invalid user information: " + e);
        }
    }

    public List<UserDomain> selectAll() throws HibernateException, DataBaseException {

        try {
            LOGGER.info("Selecting all the user from the database");

            return UserTransformer.toUserList(this.userDAO.selectAll());
        } catch (HibernateException e) {
            LOGGER.error("Database is broken");
            throw new HibernateException("Database is broken: " + e);
        }

    }
}
