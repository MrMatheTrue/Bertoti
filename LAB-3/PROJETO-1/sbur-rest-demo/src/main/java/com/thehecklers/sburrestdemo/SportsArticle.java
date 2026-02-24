package com.thehecklers.sburrestdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SportsArticle {
    private final String id;
    private String name;
    private String category;
    private Double price;

    @JsonCreator
    public SportsArticle(@JsonProperty("id") String id,
                         @JsonProperty("name") String name,
                         @JsonProperty("category") String category,
                         @JsonProperty("price") Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public SportsArticle(String name, String category, Double price) {
        this(UUID.randomUUID().toString(), name, category, price);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}