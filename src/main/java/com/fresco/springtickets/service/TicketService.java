package com.fresco.springtickets.service;


import com.fresco.springtickets.models.Ticket;
import com.fresco.springtickets.respository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    /**
     * This class acts as a service for the ticket operations
     */

    @Autowired
    private TicketRepository repository;

    /**
     * This method is used to get all the tickets saved in the database
     * @return List of all tickets in the database
     */
    public List<Ticket> getAllTickets(){
        return repository.findAll();
    }

    /**
     * This method is used to insert a ticket into the table
     * @param ticket ticket object which needs to be inserted
     */
    public void addTicket(Ticket ticket){
        repository.save(ticket);
    }

    /**
     * This method is used to query all the tickets which are handling by a specific admin
     * @param adminId Id of the admin whose tickets are needed
     * @return List of Tickets which is handled by the admin
     */
    // public List<Ticket> getTicketByAdminId(int adminId){
    //     List<Integer> ids = new ArrayList<>();
    //     ids.add(adminId);
    //     return repository.findAllById(ids);
    // }

    /**
     * This method is used to remove the ticket from the database
     * @param ticket Ticket object which needs to be removed
     */
    public void removeTicket(Ticket ticket) {
        repository.delete(ticket);

    }

    public List<Ticket> getTicketsByStatus(String status){
        return repository.findByStatus(status);
    }
}
