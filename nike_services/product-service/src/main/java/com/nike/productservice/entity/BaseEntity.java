package com.nike.productservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    private Boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = this.createdAt;
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }
}
