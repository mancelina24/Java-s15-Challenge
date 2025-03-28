package com.example.core;

import java.time.LocalDate;

public class Invoice {
private int invoiceId;
private Reader reader;
private Book book;
private double amount;
private LocalDate issueDate;
private LocalDate returnDate;

    public Invoice(int invoiceId, Reader reader, Book book, double amount) {
        this.invoiceId = invoiceId;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.issueDate = LocalDate.now();
        this.returnDate = null;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
    public  void setReturnDate(LocalDate returnDate){
        this.returnDate=returnDate;
    }
    public void display(){
        System.out.println(this);
    }
    @Override
    public String toString() {
        return "Fatura{" +
                "faturaId=" + invoiceId +
                ", okuyucu=" + reader.getName() +
                ", kitap=" + book.getName() +
                ", tutar=" + amount +
                ", düzenlemeTarihi=" + issueDate +
                ", iadeTarihi=" + returnDate +
                '}';
    }
}

