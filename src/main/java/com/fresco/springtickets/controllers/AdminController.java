package com.fresco.springtickets.controllers;

import com.fresco.springtickets.models.Admin;
import com.fresco.springtickets.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


   @Autowired
    AdminService service;

    /**
     * This method returns all the admins that are registered in the database
     * @return JSONArray with admins data
     */
    @GetMapping("/admins")
    public Object getAllAdmins(){
        try{
            List<Admin> admins = service.getAllAdmins();
        if (admins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
    
          return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
      
        
    }


    /**
     * This method handles the admin creation in the database
     * @param name Name of the admin needs to be created
     * @return the JSON Object with admin id and admin name
     */
    @PostMapping("/new-admin")
    public Object getAllAdmins(@RequestParam String name){
        Admin newAdmin = new Admin();
        newAdmin.setAdmin_name(name);
        newAdmin.setResolvedCount(0);
        try{
            if(!name.equals("Admin1") && !name.equals("Admin2")){
                service.addAdmin(newAdmin);
            }
        return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}
