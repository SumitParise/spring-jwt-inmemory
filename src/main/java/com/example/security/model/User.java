package com.example.security.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {
    private int id;
    private String Name;
    private String Email;
}
