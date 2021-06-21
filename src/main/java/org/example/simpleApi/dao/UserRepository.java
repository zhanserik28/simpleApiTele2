package org.example.simpleApi.dao;

import org.example.simpleApi.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer> {
    @Query("Select u from  UserTable  u where u.username = ?1 ")
    List<UserTable> findByUsername(String username);
}
