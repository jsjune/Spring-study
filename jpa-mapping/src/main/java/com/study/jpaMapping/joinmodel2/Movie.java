package com.study.jpaMapping.joinmodel2;

import com.study.jpaMapping.joinmodel.Item;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}
