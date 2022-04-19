package com.study.jpaMapping.collectionMapping;

import com.study.jpaMapping.joinModel.Item;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
