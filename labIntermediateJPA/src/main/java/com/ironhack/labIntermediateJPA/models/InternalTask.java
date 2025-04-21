package com.ironhack.labIntermediateJPA.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class InternalTask extends Task {

    public InternalTask() {
    }

    public InternalTask(String title, LocalDate dueDate, boolean status) {
        super(title, dueDate, status);
    }


}
