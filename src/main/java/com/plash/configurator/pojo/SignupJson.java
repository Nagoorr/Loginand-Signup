package com.plash.configurator.pojo;

import lombok.Data;


@Data
public class SignupJson {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String city;
    private String companyName;

    @Override
    public String toString() {
        return "SignupJson{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
