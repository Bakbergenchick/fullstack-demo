package com.example.books_spring.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
