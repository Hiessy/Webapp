package com.app.domain.model;


public class UserDomain {

    private Long userId;
    private Integer userCode;
    private String userName;
    private String userPass;
    private String userEmail;

    public UserDomain(Integer userCode) {
        this.userCode = userCode;
    }

    public UserDomain() {

    }

    public UserDomain(Integer userCode, String userName, String userPass, String userEmail) {
        this.userCode = userCode;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
    }



    public UserDomain(Long userId, Integer userCode, String userName, String userPass, String userEmail) {
        super();
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPass = userPass;
        this.userEmail = userEmail;
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

	@Override
	public String toString() {
		return "UserDomain [userId=" + userId + ", userCode=" + userCode
				+ ", userName=" + userName + ", userPass=" + userPass
				+ ", userEmail=" + userEmail + "]";
	}
}
