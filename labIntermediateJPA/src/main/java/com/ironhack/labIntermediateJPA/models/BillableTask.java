package com.ironhack.labIntermediateJPA.models;

import com.ironhack.labIntermediateJPA.models.Task;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class BillableTask extends Task {

    private double hourlyRate;

    public BillableTask() {
    }

    public BillableTask(String title, LocalDate dueDate, boolean status, double hourlyRate) {
        super(title, dueDate, status);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    // Getter y setter para hourlyRate
}