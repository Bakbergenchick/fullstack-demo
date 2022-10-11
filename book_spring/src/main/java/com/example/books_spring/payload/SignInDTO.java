package com.example.books_spring.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {
    private String email;
    private String password;
}
