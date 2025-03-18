package dev.gnagoli.mediamark.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private String parentId;
}
