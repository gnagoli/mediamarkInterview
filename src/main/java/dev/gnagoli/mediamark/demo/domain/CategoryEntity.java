package dev.gnagoli.mediamark.demo.domain;



import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryEntity {

    @Id
    private String id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "ref_category")
    private List<Integer> refCategory = new ArrayList<>();

    private String onlineStatus;

    private String longDescription;

    private String shortDescription;
}
