package com.example.desginpattern.builder;

import java.time.LocalDate;

public interface TourPlanBuilder {
    TourPlanBuilder newInstance();
    TourPlanBuilder nightsAndDays(int nights, int days);

    TourPlanBuilder title(String title);

    TourPlanBuilder startDate(LocalDate localDate);

    TourPlanBuilder whereToStay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);

    TourPlan getPlan();
}
