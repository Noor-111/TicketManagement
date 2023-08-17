package com.fresco.springtickets.scheduler;

import com.fresco.springtickets.models.Admin;
import com.fresco.springtickets.models.Ticket;
import com.fresco.springtickets.service.AdminService;
import com.fresco.springtickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class Schedulers {

    /**
     * This class should be responsible for the scheduled works mentioned
     * Define a method for run each 4 seconds and find unassigned tickets
     * and assign it to a admin who has least resolved counts and change
     * the ticket status to processing in the database
     *
     * Annotate the class with required Annotations
     */


    @Autowired
    TicketService service;

    @Autowired
    AdminService adminService;


    @Scheduled(fixedRate = 4000)
    public void refreshStatuses() throws InterruptedException {
        // 1. Find all unassigned tickets i.e., status = registered
        List<Ticket> tickets = service.getTicketsByStatus("registered");
        tickets.stream()
        //.filter(t->t.getStatus().equals("registered"))
        .forEach(ticket -> {
            // 2. find an admin with least resolvedCount val
            int adminId = adminService.getLowestResolvedAdmin();
            Ticket ticketNew = new Ticket();
            ticketNew.setTicket_id(ticket.getTicket_id());
            ticketNew.setContent(ticket.getContent());
            ticketNew.setSender_name(ticket.getSender_name());
            //3. set the adminId 
            ticketNew.setAdmin_id(adminId);
            ticketNew.setStatus("processing");
            //4. update ticket with new admin
            service.addTicket(ticketNew);

            // 5. increment the admin's resolved count
            // Admin admin = adminService.getAdmin(adminId);
            // admin.setResolvedCount(admin.getResolvedCount()+1);

            // adminService.addAdmin(admin);
            

        });
        
        // 2.1 

    }

}
