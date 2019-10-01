package com.plash.configurator.controller;

import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.LoginResponseJson;

import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.pojo.SignupJson;
import com.plash.configurator.service.WebSignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@RestController
@RequestMapping("/websignup")
public class WebSignupController {

 @Autowired
private WebSignupService webSignupService;

    final static Logger logger = LoggerFactory.getLogger(WebSignupController.class);


     @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public  ResponseEntity<ResponseCodeJson> signup(@RequestBody SignupJson signupJson) throws Exception
                {
              logger.info("Inside signup:"+signupJson);
              Assert.notNull(signupJson.getEmail(),"Email should not be empty");
              Assert.notNull(signupJson.getUsername(),"first name should not be empty");
              Assert.notNull(signupJson.getPassword(),"password should not be empty");
              Assert.notNull(signupJson.getCity(),"city should not be empty");
              Assert.notNull(signupJson.getCompanyName(),"Company Name Should not be null");
              ExceptionThrower exceptionThrower=new ExceptionThrower();
              LoginResponseJson loginResponseJson=webSignupService.signup(signupJson);
                    ResponseCodeJson responseCodeJson=loginResponseJson.getStatuscode();
                    if (responseCodeJson.getErrorcode()!=200){
                        exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(), responseCodeJson.getMessage());
                    }
                    loginResponseJson.setStatuscode(null);
                    logger.info("Response Json: " + loginResponseJson);
                    return new ResponseEntity<>(responseCodeJson, HttpStatus.OK);
                }
}
