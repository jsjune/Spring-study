package com.study.jpaMapping.joinModel2;

import com.study.jpaMapping.joinModel.Item;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
