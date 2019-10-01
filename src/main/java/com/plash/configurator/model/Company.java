package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMPANY" )
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "COMPANYID" )
    private Long companyId;

    @Column(name = "COMPANYNAME")
    private String companyName;


}
