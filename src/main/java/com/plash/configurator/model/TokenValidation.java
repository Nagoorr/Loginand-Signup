package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TOKENVALIDATION", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class TokenValidation {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERID")
    private Long userid;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "EXPIRYDATE")
    private Date expirydate;

    @Column(name = "LOGDATE")
    private Date logdate;


}
