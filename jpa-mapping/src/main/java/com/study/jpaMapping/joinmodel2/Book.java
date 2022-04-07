package com.study.jpaMapping.joinmodel2;

import com.study.jpaMapping.joinmodel.Item;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String author;
    private String isbn;
}
