package com.plash.configurator.service;


import com.plash.configurator.config.UserPrincipal;
import com.plash.configurator.model.*;
import com.plash.configurator.pojo.LoginResponseJson;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.pojo.WebLoginJson;
import com.plash.configurator.repository.*;
import com.plash.configurator.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;


import java.util.Date;

@Service
public class WebLoginService implements UserDetailsService {

    @Autowired
    private WebLoginRepository webLoginRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenValidationRepository  tokenValidationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=usersRepository.findByUseremailid(username);

        return new UserPrincipal(user);
    }

    public LoginResponseJson webLogin(WebLoginJson webLoginJson) {
        LoginResponseJson loginResponseJson = new LoginResponseJson();
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();

        String email =webLoginJson.getEmail().toLowerCase();
        email=email.trim();

        String password = webLoginJson.getPassword();
        password=password.trim();


        Users users = webLoginRepository.findByMailGetUsers(email);
        if (users == null) {
            responseCodeJson.setMessage("Email dosen't exists please register");
            responseCodeJson.setErrorcode(404);
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }

        Users users1 = webLoginRepository.findUsersByEmailAndPassword(email, password);
        if (users1 == null) {
            responseCodeJson.setMessage("Invalid username or password");
            responseCodeJson.setErrorcode(401);
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }
        if (!password.equals(users1.getPassword())) {
            responseCodeJson.setMessage("Invalid password");
            responseCodeJson.setErrorcode(402);
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }
        responseCodeJson.setMessage("Success");
        responseCodeJson.setErrorcode(200);
        loginResponseJson.setStatuscode(responseCodeJson);
        loginResponseJson = setToken(loginResponseJson);
        users1.setPassword(null);
        loginResponseJson.setUser(users1);
        return loginResponseJson;
    }

    public LoginResponseJson setToken(LoginResponseJson loginResponseJson)
    {
        String token= RandomNumber.uniqueRandomIdentifier();
        TokenValidation tokenValidation=new TokenValidation();
        Date now=new Date();
        LocalDateTime localDateTime=LocalDateTime.now().plusDays(60);
        tokenValidation.setExpirydate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        tokenValidation.setLogdate(now);
        tokenValidation.setToken(token);
        tokenValidationRepository.save(tokenValidation);
        loginResponseJson.setToken(token);
        return loginResponseJson;
        }
}



