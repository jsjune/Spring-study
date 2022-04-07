package com.study.jpaMapping.joinmodel;

import javax.persistence.Entity;

@Entity
public class Movie extends Item{
    private String director;
    private String actor;
}
