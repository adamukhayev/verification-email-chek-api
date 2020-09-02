package com.m46.codechecks.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "day", nullable = false)
    private Integer day;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "gender", nullable = false)
    private String gender;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "phone", nullable = true)
    private String phone;
}
