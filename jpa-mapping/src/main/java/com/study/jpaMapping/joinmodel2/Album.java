package com.study.jpaMapping.joinmodel2;

import com.study.jpaMapping.joinmodel.Item;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
