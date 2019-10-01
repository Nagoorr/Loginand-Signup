package com.plash.configurator.controller;

import com.plash.configurator.exception.ExceptionThrower;
import com.plash.configurator.pojo.LoginResponseJson;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.pojo.WebLoginJson;
import com.plash.configurator.service.WebLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.util.Assert;

@RestController
@RequestMapping("/weblogin")
public class WebLoginController {

    final static  Logger logger=LoggerFactory.getLogger(WebLoginController.class);

    @Autowired
    private WebLoginService webLoginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<LoginResponseJson> webLogin(@RequestBody WebLoginJson webLoginJson) throws Exception
    {
       logger.info("Inside weblogin:"+webLoginJson);
        ExceptionThrower exceptionThrower=new ExceptionThrower();
        Assert.notNull(webLoginJson.getEmail(),"Email Should Not be Empty");
        Assert.notNull(webLoginJson.getPassword(),"password should not be empty");
        LoginResponseJson loginResponseJson=webLoginService.webLogin(webLoginJson);
        ResponseCodeJson responseCodeJson=loginResponseJson.getStatuscode();
        if (responseCodeJson.getErrorcode()!=200){
            exceptionThrower.throwCustomException(responseCodeJson.getErrorcode(),responseCodeJson.getMessage());
            logger.info("Inside Exception");
        }
        logger.info("Response Json: "+loginResponseJson);
        loginResponseJson.setStatuscode(null);
        return new ResponseEntity<>(loginResponseJson, HttpStatus.OK);
    }
}
