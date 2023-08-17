package com.fresco.springtickets.respository;

import com.fresco.springtickets.models.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// @Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    /**
     * This should be a repository for the admins database
     * Make relevant inheritance to make a repository with Ticket class
     */
    List<Admin> findByOrderByResolvedCount();
}
