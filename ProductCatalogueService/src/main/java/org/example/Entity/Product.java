package org.example.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@NamedQuery(name = "Product.findActiveByCategory", query = "SELECT p from Product p where p.category = :ctgry ORDER BY p.price DESC")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean active;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    protected Product() {}
    public Product(String name, String category, BigDecimal price, boolean active) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.active = active;
    }
}
