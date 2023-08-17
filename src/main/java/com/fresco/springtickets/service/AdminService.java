package com.fresco.springtickets.service;

import com.fresco.springtickets.models.Admin;
import com.fresco.springtickets.respository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;


    /**
     * This method finds the admin who has the least no of resolved tickets
     * so that the ticket that is going to be assigned can be assigned to him
     * @return adminid of admin having least resolved admins
     */
    public int getLowestResolvedAdmin(){
        return repository.findByOrderByResolvedCount().get(0).getAdmin_id();
    }

    /**
     * This method is to query all the admins that are available in the
     * database
     * @return List of Admins
     */
    public List<Admin> getAllAdmins(){
        return repository.findAll();
    }

    /**
     * This method is used to insert a new admin to the database
     * @param admin object that needs to be inserted into the database
     */
    public void addAdmin(Admin admin){
        repository.save(admin);
    }

    public Admin getAdmin(int id){
        return repository.findById(id).get();
    }

}
