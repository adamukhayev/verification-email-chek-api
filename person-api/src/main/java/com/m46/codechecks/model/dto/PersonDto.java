package com.m46.codechecks.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDto {

    private Long id;

    private String name;

    private String surname;

    private Integer day;

    private String month;

    private Integer year;

    private String gender;

    private String email;

    private String password;

    private String phone;
}
