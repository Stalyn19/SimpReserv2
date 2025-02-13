package com.simpreserv.repository;

import com.simpreserv.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Long countByUserId(Integer userId);

  @Query("SELECT u FROM User u WHERE u.email = ?1") User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE CONCAT(u.userId, u.userTypeId, u.email, u.enabled) LIKE %?1%")
  public List<User> findAll(String keyword);

}
