package oms.backend.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oms.backend.models.User;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

    List<User> findByemail(String email);

}