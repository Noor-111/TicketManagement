package com.fresco.springtickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {

    /**
     * This is a model for Admin which is also used to
     * connect to the database
     * TODO Annotate with revelant annotations for making "admins" table
     */


    /*
    admin_id is the primary key in the database and
    this value should be a number which is autogenerated
     */

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int admin_id;


    @Column(name = "resolved_count")
    private int resolvedCount;


    private String admin_name;

}
