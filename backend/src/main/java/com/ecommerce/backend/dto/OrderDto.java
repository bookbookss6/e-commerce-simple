package com.ecommerce.backend.dto;

import lombok.Getter;

@Getter
public class OrderDto {

    private String title;
    private String address;
    private String payment;
    private String cardNumber;
    private String items;
}