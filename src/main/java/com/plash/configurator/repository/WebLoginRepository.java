package com.plash.configurator.repository;
import com.plash.configurator.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebLoginRepository extends JpaRepository<Users ,Long> {

         @Query("Select us from Users as  us where us.useremailid=:email")
         public  Users findByMailGetUsers(@Param("email") String email);

         @Query("Select us from Users as us where us.useremailid=:email and us.password=:pass")
         public  Users findUsersByEmailAndPassword(@Param("email") String email,@Param("pass") String password);
}
