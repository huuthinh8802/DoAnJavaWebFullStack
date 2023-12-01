package com.watch.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.watch.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
