package com.app.persistence.sql;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.persistence.sql.common.UserEntity;

public class HibernateUtil {
	
	private static Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class.getName());
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Properties prop = new Properties();
			prop.setProperty("hibernate.connection.url",
					"jdbc:mysql://192.168.1.200:3306/hiessy");
			prop.setProperty("hibernate.connection.username", "root");
			prop.setProperty("hibernate.connection.password", "pmdj2424");
			prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

			return new AnnotationConfiguration()			   
			   .addAnnotatedClass(UserEntity.class).addProperties(prop) // stock.class in null position
			   .buildSessionFactory();

		} catch (Throwable ex) {
			LOGGER.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		LOGGER.info("Session initialized succesfully.");
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
		LOGGER.info("Session closed succesfully.");
	}

}