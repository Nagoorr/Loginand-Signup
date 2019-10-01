package com.plash.configurator.service;

import com.plash.configurator.model.Company;
import com.plash.configurator.model.Users;
import com.plash.configurator.pojo.LoginResponseJson;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public LoginResponseJson getUser(Long userId)
    {
        LoginResponseJson loginResponseJson = new LoginResponseJson();
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();

        Users users=usersRepository.findById(userId);
        if (users==null)
        {
            responseCodeJson.setMessage("User dosen't exists please register");
            responseCodeJson.setErrorcode(404);
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }
        Company company=users.getCompany();
        if (company==null)
        {
            responseCodeJson.setMessage("User Not registered with company");
            responseCodeJson.setErrorcode(401);
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }
        responseCodeJson.setMessage("Success");
        responseCodeJson.setErrorcode(200);
        loginResponseJson.setStatuscode(responseCodeJson);
        users.setPassword(null);
        loginResponseJson.setUser(users);
        return loginResponseJson;
    }
}
