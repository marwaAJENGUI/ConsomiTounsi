package tn.consomitounsi.www.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.consomitounsi.www.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
