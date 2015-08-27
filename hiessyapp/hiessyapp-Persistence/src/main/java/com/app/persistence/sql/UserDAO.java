package com.app.persistence.sql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.app.persistence.sql.common.UserEntity;

public class UserDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class.getName());
    private static final String TABLE = "hiessy.users";

    public Long save(UserEntity user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Long id = (Long) session.save(user);
            session.getTransaction().commit();

            LOGGER.info("User added succesfully to the database");
            HibernateUtil.shutdown();
            return id;
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    public void delete(Integer userCode) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_CODE='" + userCode + "'").list().get(0);

            session.delete(session.get(UserEntity.class, id.longValue()));
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            
        } catch (Exception e) {
            LOGGER.error("Unable to delete selected user: " + e.getMessage());
            HibernateUtil.shutdown();
        }
    }

    public void delete(String userEmail) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_EMAIL='" + userEmail + "'").list().get(0);

            session.delete(session.get(UserEntity.class, id.longValue()));
            session.getTransaction().commit();
            HibernateUtil.shutdown();
        } catch (Exception e) {
            HibernateUtil.shutdown();
            LOGGER.error("Unable to delete selected user: " + e.getMessage());
        }
    }

    public UserEntity select(String userEmail) {
        try {
        	UserEntity userInfo = new UserEntity();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_EMAIL='" + userEmail + "'").list().get(0);

            userInfo = (UserEntity) session.get(UserEntity.class, id.longValue());
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return userInfo;
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    public UserEntity select(Integer stockCode) {
        try {
            UserEntity userInfo = new UserEntity();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_CODE='" + stockCode + "'").list().get(0);

            userInfo = (UserEntity) session.get(UserEntity.class, id.longValue());
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return userInfo;
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    // TODO problem retrieving data is modifying email can't search by email.
    public UserEntity update(String userEmail, UserEntity user) {
        try {
        	UserEntity userInfo = new UserEntity();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_EMAIL='" + userEmail + "'").list().get(0);

            userInfo = this.updaterUser((UserEntity) session.get(UserEntity.class, id.longValue()), user);

            session.saveOrUpdate(userInfo);

            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return userInfo;
            
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    // TODO problem retrieving data is modifying code can't search by code.
    public UserEntity update(Integer stockCode, UserEntity stock) {
        try {
        	UserEntity stockuser = new UserEntity();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Number id = (Number) session
                .createSQLQuery("SELECT USER_ID FROM " + TABLE + " WHERE USER_CODE='" + stockCode + "'").list().get(0);

            stockuser = this.updaterUser((UserEntity) session.get(UserEntity.class, id.longValue()), stock);

            session.saveOrUpdate(stockuser);

            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return stockuser;
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> selectAll() throws HibernateException {
        try {
            List<UserEntity> userList = new ArrayList<UserEntity>();

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            userList = session.createCriteria(UserEntity.class).list();
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return userList;
        } catch (Exception e) {
            HibernateUtil.shutdown();
            return null;
        }
    }

    private UserEntity updaterUser(UserEntity a, UserEntity b) {

        if (b.getUserCode() != null) {
            a.setUserCode(b.getUserCode());
        }
        if (b.getUserEmail() != null) {
            a.setUserEmail(b.getUserEmail());
        }
        if (b.getUserName() != null) {
            a.setUserName(b.getUserName());
        }
        if (b.getUserPass() != null) {
            a.setUserPass(b.getUserPass());
        }

        return a;
    }
}
