package dev.gnagoli.mediamark.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private String parentId;
}
