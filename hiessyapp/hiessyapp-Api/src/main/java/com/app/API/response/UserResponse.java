package com.app.API.response;

import java.io.Serializable;

public class UserResponse<T>
    implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserMetaData userMetaData;
    private T userData;

    public UserResponse() {
        super();
    }

	public UserMetaData getUserMetaData() {
		return userMetaData;
	}

	public void setUserMetaData(UserMetaData userMetaData) {
		this.userMetaData = userMetaData;
	}

	public T getUserData() {
		return userData;
	}

	public void setUserData(T userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "UserResponse [userMetaData=" + userMetaData + ", userData="
				+ userData + "]";
	}

}
