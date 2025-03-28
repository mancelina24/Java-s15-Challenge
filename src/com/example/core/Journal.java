package com.example.core;

import java.time.LocalDate;

public class Journal extends Book {
    private final String issn;
    private String frequency;

    public Journal(int bookId, String name, Author author, double price, String status, String edition, LocalDate dateOfPurchase, Reader currentOwner, String category, String issn, String frequency) {
        super(bookId, name, author, price, status, edition, dateOfPurchase, currentOwner, category);
        this.issn = issn;
        this.frequency = frequency;
    }

    public String getIssn() {
        return issn;
    }

    public String getFrequency() {
        return frequency;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Dergi{" + super.toString() +
                ", issn='" + issn + '\'' +
                ", yayınSıklığı='" + frequency + '\'' +
                '}';
    }
}
