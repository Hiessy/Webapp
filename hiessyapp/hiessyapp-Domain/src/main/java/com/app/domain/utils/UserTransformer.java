package com.app.domain.utils;

import java.util.ArrayList;
import java.util.List;

import com.app.domain.model.UserDomain;
import com.app.persistence.sql.common.UserEntity;

public class UserTransformer {

    public static UserEntity toPersistenceObject(UserDomain user) {

    	UserEntity userPersistence = new UserEntity();
        userPersistence.setUserCode(user.getUserCode());
        userPersistence.setUserName(user.getUserName());
        userPersistence.setUserEmail(user.getUserEmail());
        userPersistence.setUserPass(user.getUserPass());

        return userPersistence;
    }

    public static UserDomain toUserObject(UserEntity userPersistence) {
        UserDomain user = new UserDomain();
        user.setUserId(userPersistence.getUserId());
        user.setUserCode(userPersistence.getUserCode());
        user.setUserName(userPersistence.getUserName());
        user.setUserEmail(userPersistence.getUserEmail());
        user.setUserPass(userPersistence.getUserPass());

        return user;
    }

    public static List<UserDomain> toUserList(List<UserEntity> userTableList) {
        List<UserDomain> results = new ArrayList<UserDomain>();

        for (int i = 0; i < userTableList.size(); i++) {
            results.add(new UserDomain(userTableList.get(i).getUserId(), userTableList.get(i).getUserCode(),
                userTableList.get(i).getUserName(), userTableList.get(i).getUserEmail(), userTableList.get(i)
                    .getUserPass()));
        }
        return results;
    }
}
