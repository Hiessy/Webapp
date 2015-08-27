package com.app.service.util;

import java.util.ArrayList;
import java.util.List;

import com.app.API.request.UserDto;
import com.app.domain.model.UserDomain;


public class UserConverter {

    private UserDomain user = new UserDomain();
    private UserDto userRequest = new UserDto();

    public UserDomain requestToUser(UserDto userRequest) {

        this.user.setUserCode(userRequest.getUserCode());
        this.user.setUserName(userRequest.getUserName());
        this.user.setUserEmail(userRequest.getUserEmail());
        this.user.setUserPass(userRequest.getUserPass());

        return this.user;
    }

    public UserDto userToRequest(UserDomain user) {

        this.userRequest.setUserId(user.getUserId());
        this.userRequest.setUserCode(user.getUserCode());
        this.userRequest.setUserName(user.getUserName());
        this.userRequest.setUserEmail(user.getUserEmail());
        this.userRequest.setUserPass(user.getUserPass());

        return this.userRequest;
    }

    public List<UserDto> toRequestList(List<UserDomain> userList) {
        List<UserDto> results = new ArrayList<UserDto>();

        for (int i = 0; i < userList.size(); i++) {
            results.add(new UserDto(userList.get(i).getUserId(), userList.get(i).getUserCode(), userList.get(i)
                .getUserName(), userList.get(i).getUserEmail(), userList.get(i).getUserPass()));

        }
        return results;
    }
}
