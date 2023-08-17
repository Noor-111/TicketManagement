package com.fresco.springtickets.controllers;

import com.fresco.springtickets.models.Ticket;
import com.fresco.springtickets.service.TicketService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {


    @Autowired
    TicketService service;


    /**
     * This method returns the json array of all the tickets registered in the
     * application's database
     * @return JSONArray with the list of tickets registered
     */
    @GetMapping("/tickets")
    public Object getAllTickets() {
        try{
            List<Ticket> tickets = service.getAllTickets();
        if (tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
    
          return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    /**
     * This method creates and saves the ticket into the database and
     *  this is responsible for initialization of the ticket data
     * @param sender The name of the user who registers the ticket
     * @param content The message description of the ticket
     * @return JSONObject with the ticket data
     */
    @PostMapping("/new-ticket")
    public Object insertTicket(@RequestParam String sender, @RequestParam String content) {
        Ticket ticket = new Ticket();
        ticket.setSender_name(sender);
        ticket.setContent(content);
        ticket.setStatus("registered");
        ticket.setAdmin_id(0);
        try{
            service.addTicket(ticket);;
        return  new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
