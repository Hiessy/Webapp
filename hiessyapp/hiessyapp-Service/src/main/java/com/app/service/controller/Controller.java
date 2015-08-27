package com.app.service.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.API.request.UserDto;
import com.app.API.response.UserMetaData;
import com.app.API.response.UserResponse;
import com.app.domain.model.UserDomain;
import com.app.domain.service.UserManager;
import com.app.service.util.UserConverter;

@RestController
@RequestMapping(value = "/manager/users")
public class Controller {
    private static Logger LOGGER = LoggerFactory.getLogger(Controller.class.getName());
    UserManager userManager = new UserManager();
    UserConverter userConverter = new UserConverter();
    UserDomain user = new UserDomain();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<UserResponse<UserDto>> add(@RequestBody UserDto userDto) throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.user = this.userConverter.requestToUser(userDto);
            this.userManager.addUser(this.user);

            userMetaData.setInfo("User created");
            userMetaData.setHttpStatus(HttpStatus.OK);

            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(this.userConverter.userToRequest(this.user));
            LOGGER.info("User was added succesfully: " + userResponse.toString());

        } catch (Exception e) {
            userMetaData.setInfo("User not created");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error("User not created: " + e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", params = {"email"}, method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse<UserDto>> delete(@RequestParam("email") String code) throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.userManager.delete(code);
            userMetaData.setInfo("User Deleted");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.info("User was deleted succesfully");

        } catch (Exception e) {

            userMetaData.setInfo("User not deleted");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error("User not deleted" + e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", params = {"code"}, method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse<UserDto>> delete(@RequestParam("code") Integer code) throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.userManager.delete(code);
            userMetaData.setInfo("User Deleted");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.info("User was deleted succesfully");

        } catch (Exception e) {

            userMetaData.setInfo("User not deleted");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error("User not deleted" + e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/select", params = {"email"}, method = RequestMethod.GET)
    public ResponseEntity<UserResponse<UserDto>> select(@RequestParam("email") String code) throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.user = this.userManager.select(code);
            userMetaData.setInfo("User selected");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(this.userConverter.userToRequest(this.user));

            return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);

        } catch (Exception e) {
            userMetaData.setInfo("User not found");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/select", params = {"code"}, method = RequestMethod.GET)
    public ResponseEntity<UserResponse<UserDto>> select(@RequestParam("code") Integer code) throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.user = this.userManager.select(code);

            userMetaData.setInfo("User selected");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(this.userConverter.userToRequest(this.user));

            return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);

        } catch (Exception e) {
            userMetaData.setInfo("User not found");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", params = {"email"}, method = RequestMethod.PUT)
    public ResponseEntity<UserResponse<UserDto>> update(@RequestParam("email") String code, @RequestBody UserDto userDto)
        throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.user = this.userConverter.requestToUser(userDto);
            this.user = this.userManager.update(code, this.user);
            userMetaData.setInfo("User selected");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(this.userConverter.userToRequest(this.user));

            return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);

        } catch (Exception e) {
            userMetaData.setInfo("User not found");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", params = {"code"}, method = RequestMethod.PUT)
    public ResponseEntity<UserResponse<UserDto>> update(@RequestParam("code") Integer code, @RequestBody UserDto userDto)
        throws IOException {

        UserResponse<UserDto> userResponse = new UserResponse<UserDto>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            this.user = this.userConverter.requestToUser(userDto);
            this.user = this.userManager.update(code, this.user);

            userMetaData.setInfo("User selected");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(this.userConverter.userToRequest(this.user));

            return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);

        } catch (Exception e) {
            userMetaData.setInfo("User not found");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<UserResponse<UserDto>>(userResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/selectall", method = RequestMethod.GET)
    public ResponseEntity<UserResponse<List<UserDto>>> selectAll() throws IOException {

        UserResponse<List<UserDto>> userResponse = new UserResponse<List<UserDto>>();
        UserMetaData userMetaData = new UserMetaData();

        try {
            List<UserDto> userData = this.userConverter.toRequestList(this.userManager.selectAll());
            userMetaData.setInfo("User selected");
            userMetaData.setHttpStatus(HttpStatus.OK);
            userResponse.setUserMetaData(userMetaData);
            userResponse.setUserData(userData);
        } catch (Exception e) {
            userMetaData.setInfo("User not found");
            userMetaData.setHttpStatus(HttpStatus.BAD_REQUEST);
            userResponse.setUserMetaData(userMetaData);
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<UserResponse<List<UserDto>>>(userResponse, HttpStatus.OK);
    }
}
