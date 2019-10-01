package com.plash.configurator.service;


import com.plash.configurator.model.*;
import com.plash.configurator.pojo.LoginResponseJson;
import com.plash.configurator.pojo.ResponseCodeJson;
import com.plash.configurator.pojo.SignupJson;
import com.plash.configurator.repository.*;
import com.plash.configurator.utils.AtomicIdCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebSignupService {

    final static Logger logger = LoggerFactory.getLogger(WebSignupService.class);


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Transactional
    public LoginResponseJson signup(SignupJson signupJson) {
        LoginResponseJson loginResponseJson = new LoginResponseJson();
        ResponseCodeJson responseCodeJson = new ResponseCodeJson();

        String email = signupJson.getEmail().toLowerCase();
        email = email.trim();
        String password = signupJson.getPassword();
        password = password.trim();
        String userName = signupJson.getUsername();
        String city = signupJson.getCity();
        String companyName=signupJson.getCompanyName();

        Users user = usersRepository.findByUseremailid(email);
        Company company=companyRepository.findByCompanyName(companyName);
        if (user != null) {
            if (email.equalsIgnoreCase("")) {
                responseCodeJson.setErrorcode(402);
                responseCodeJson.setMessage("Email Can't be Empty");
                loginResponseJson.setStatuscode(responseCodeJson);
                return loginResponseJson;
            }
            if (user.getUseremailid().equals(email)) {
                responseCodeJson.setErrorcode(404);
                responseCodeJson.setMessage("Email id already Exist");
                loginResponseJson.setStatuscode(responseCodeJson);
                return loginResponseJson;
            }
        }

        if (company==null)
        {
            company=new Company();
           company.setCompanyName(companyName);
           companyRepository.save(company);
        }

            user = new Users();
            user.setUseremailid(email);
            user.setPassword(password);
            user.setCity(city);
            user.setUsername(userName);
            user.setCompany(company);
        try {
            usersRepository.save(user);
        } catch (Exception e) {
            responseCodeJson.setErrorcode(400);
            responseCodeJson.setMessage(" unable to save book data");
            loginResponseJson.setStatuscode(responseCodeJson);
            return loginResponseJson;
        }
        responseCodeJson.setErrorcode(200);
        responseCodeJson.setMessage("Success");
        loginResponseJson.setStatuscode(responseCodeJson);
        user.setPassword(null);
        loginResponseJson.setUser(user);
        return loginResponseJson;
    }
}
