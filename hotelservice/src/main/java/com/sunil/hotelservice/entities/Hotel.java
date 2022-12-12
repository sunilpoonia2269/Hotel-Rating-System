package com.sunil.hotelservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "hotels", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "location" }))
public class Hotel {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String location;

    @Column(length = 1000)
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
