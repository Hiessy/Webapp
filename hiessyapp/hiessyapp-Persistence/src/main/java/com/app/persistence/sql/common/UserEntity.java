package com.app.persistence.sql.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity
    implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "USER_CODE", nullable = false, length = 10)
    private Integer userCode;

    @Column(name = "USER_NAME", nullable = false, length = 20)
    private String userName;

    @Column(name = "USER_PASS", nullable = false, length = 20)
    private String userPass;

    @Column(name = "USER_EMAIL", nullable = false, length = 20)
    private String userEmail;

    public UserEntity() {
    }

    public UserEntity(Long userId) {
        this.userId = userId;
    }

    public UserEntity(Long userId, Integer userCode, String userName, String userPass, String userEmail) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
    }

    public UserEntity(Integer userCode, String userName, String userPass, String userEmail) {
        this.userCode = userCode;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
    }

    public UserEntity(Long userId, Integer userCode, String userName) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
