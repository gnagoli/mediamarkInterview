package dev.gnagoli.mediamark.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CategoryEntity {

    @Id
    private String id;

    private String name;

    private String parentId;

    public CategoryEntity() {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
