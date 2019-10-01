/**
 * 
 */
package com.plash.configurator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USERS" )
public class Users {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "USEREMAILID")
	private String useremailid;


	@Column(name = "USERNAME")
	private String username;


	@Column(name = "PASSWORD")
	private String password;


    @Column(name = "CITY")
    private String city;

    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COMPANYID")
    private Company company;


}
