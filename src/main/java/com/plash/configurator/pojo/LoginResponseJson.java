package com.plash.configurator.pojo;


import com.plash.configurator.model.Users;
import lombok.Data;

import java.util.Date;


@Data
public class LoginResponseJson {

    private ResponseCodeJson statuscode;
    private Long userid;
    private Users user;
    private Date lastlogin;
    private Integer loginmode;
    private String token;

    @Override
    public String toString() {
        return "LoginResponseJson{" +
                "statuscode=" + statuscode +
                ", userid=" + userid +
                ", user=" + user +
                ", lastlogin=" + lastlogin +
                ", loginmode=" + loginmode +
                ", token='" + token + '\'' +
                '}';
    }
}
