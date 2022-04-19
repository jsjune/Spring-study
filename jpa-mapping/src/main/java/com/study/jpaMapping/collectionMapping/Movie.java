package com.study.jpaMapping.collectionMapping;

import com.study.jpaMapping.joinModel.Item;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}
