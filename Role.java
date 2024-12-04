package com.example.rbac.rbac.model;

import jakarta.persistence.*;

@Entity
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String name;
    }


