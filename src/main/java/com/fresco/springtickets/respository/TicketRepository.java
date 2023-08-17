package com.fresco.springtickets.respository;


import com.fresco.springtickets.models.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
import java.util.List;

// @Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    /**
     * This should be a repository for the Ticket database
     * Make relevant inheritance to make a repository with Ticket class
     */
    // @Query("select t from tickets t where t.admin_id = ?1")
    // List<Ticket> findByAdminId(int adminId);


    List<Ticket> findByStatus(String status);

}
