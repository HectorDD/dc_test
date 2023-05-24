package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<String> keywords;

    public List<Category> children;

    public Category(String name, List<String> keywords) {
        this.name = name;
        this.keywords = keywords;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addChildren(Category category) {
        this.children.add(category);
    }


    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
