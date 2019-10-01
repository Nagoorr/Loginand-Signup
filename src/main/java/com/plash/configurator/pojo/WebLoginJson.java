package com.plash.configurator.pojo;

import lombok.Data;

@Data
public class WebLoginJson {

    private String email;
    private String password;
    private String token;

    @Override
    public String toString() {
        return "WebLoginJson{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='"+token+'\''+
                '}';
    }
}
