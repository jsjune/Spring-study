package com.study.jpaMapping.joinModel2;

import com.study.jpaMapping.joinModel.Item;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String author;
    private String isbn;
}
