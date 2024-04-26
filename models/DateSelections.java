package com.capstone.capstone_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateSelections {
    private String reviewPhase;
    private List<LocalDate> selectedDates;

    public String getReviewPhase() {
        return reviewPhase;
    }

    public void setReviewPhase(String reviewPhase) {
        this.reviewPhase = reviewPhase;
    }

    public List<LocalDate> getSelectedDates() {
        return selectedDates;
    }

    public void setSelectedDates(List<LocalDate> selectedDates) {
        this.selectedDates = selectedDates;
    }
}
