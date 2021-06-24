package com.example.soapapis.repositories;

import com.example.soapapis.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByFullName(String fullName);

}
