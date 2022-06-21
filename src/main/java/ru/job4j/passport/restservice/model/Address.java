package ru.job4j.passport.restservice.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String house;
    private String apartment;
}
