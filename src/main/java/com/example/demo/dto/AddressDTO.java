package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private String city;
    private String street;
    private String zipCode;

}
