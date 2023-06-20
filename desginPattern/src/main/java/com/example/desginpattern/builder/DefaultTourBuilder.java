package com.example.desginpattern.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DefaultTourBuilder implements TourPlanBuilder{
    private TourPlan tourPlan;

    @Override
    public TourPlanBuilder newInstance() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
        this.tourPlan.setNights(nights);
        this.tourPlan.setDays(days);
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        this.tourPlan.setStartDate(startDate);
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.tourPlan.setWhereToStay(whereToStay);
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        if (this.tourPlan.getPlans() == null) {
            this.tourPlan.setPlans(new ArrayList<>());
        }

        this.tourPlan.getPlans().add(new DetailPlan(day, plan));
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return this.tourPlan;
    }
}
