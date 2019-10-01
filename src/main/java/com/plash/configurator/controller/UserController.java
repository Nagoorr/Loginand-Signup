package com.plash.configurator.controller;

import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.LoginResponseJson;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.util.Assert;

@RestController
@RequestMapping("/user")
public class UserController {

    final static  Logger logger=LoggerFactory.getLogger(WebLoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userid/{userID}",method = RequestMethod.GET)
    public ResponseEntity<LoginResponseJson> getUser(@PathVariable("userID") Long userId) throws Exception
    {
        logger.info("Inside getuser"+userId);
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        Assert.notNull(userId,"userId Should not be null");
        LoginResponseJson loginResponseJson = userService.getUser(userId);
        ResponseCodeJson responseCodeJson = loginResponseJson.getStatuscode();
        logger.info("loginResponseJson:"+loginResponseJson);
        logger.info("responseCodeJson:"+responseCodeJson);
        if (responseCodeJson.getErrorcode()!= 200) {
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
            logger.info("Inside exception");
        }
        logger.info("success"+loginResponseJson);
        loginResponseJson.setStatuscode(null);
        return new ResponseEntity<>(loginResponseJson, HttpStatus.OK);
    }
}
