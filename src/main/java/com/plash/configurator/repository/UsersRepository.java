package com.plash.configurator.repository;

import com.plash.configurator.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Users findByUseremailid(String useremailid);

    Users findById(Long id);

}
