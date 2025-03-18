package dev.gnagoli.mediamark.demo.domain;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductEntity {

    @Id
    private String id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "ref_category")
    private List<Integer> refCategory = new ArrayList<>();

    private String onlineStatus;

    private String longDescription;

    private String shortDescription;

    public ProductEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRefCategory() {
        return refCategory;
    }

    public void setRefCategory(List<Integer> refCategory) {
        this.refCategory = refCategory;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
