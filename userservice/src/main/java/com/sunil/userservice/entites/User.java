package com.sunil.userservice.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.sunil.userservice.payloads.RatingDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
public class User {

    @Id
    private String id;

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    private String about;

    @Transient
    private List<RatingDto> ratings = new ArrayList<>();

}
