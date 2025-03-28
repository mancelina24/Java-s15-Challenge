package com.example.core;

import java.time.LocalDate;

public class Magazine extends Book{
    private String issueNumber;
    private String publisher;

    public Magazine(int bookId, String name, Author author, double price, String status, String edition,
                    LocalDate dateOfPurchase, Reader currentOwner, String category, String issueNumber, String publisher) {
        super(bookId, name, author, price, status, edition, dateOfPurchase, currentOwner, category);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Dergi{" + super.toString() +
                ", SayıNumarası='" + issueNumber + '\'' +
                ", Yayıncı='" + publisher + '\'' +
                '}';
    }
}
