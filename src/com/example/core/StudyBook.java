package com.example.core;

import java.time.LocalDate;

public class StudyBook extends Book{
    private String subject;
    private String grade;

    public StudyBook(int bookId, String name, Author author, double price, String status, String edition,
                     LocalDate dateOfPurchase, Reader currentOwner, String category, String subject, String grade) {
        super(bookId, name, author, price, status, edition, dateOfPurchase, currentOwner, category);
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Ders Kitabı{" + super.toString() +
                ", Konu='" + subject + '\'' +
                ", Seviye='" + grade + '\'' +
                '}';
    }
}
