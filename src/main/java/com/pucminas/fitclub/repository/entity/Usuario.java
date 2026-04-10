package com.pucminas.fitclub.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "uauario")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private String password;
    private String role; // ADMIN ou USER
}
