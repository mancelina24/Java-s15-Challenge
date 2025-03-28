package com.example.core;

import java.time.LocalDate;

public class Book {
    private int bookId;
    private String name;
    private Author author;
    private double price;
    private String status; // Available, Borrowed
    private String edition;
    private LocalDate dateOfPurchase;
    private Reader currentOwner;
    private String category;

    public Book(int bookId, String name, Author author, double price, String status, String edition,
                LocalDate dateOfPurchase, Reader currentOwner, String category) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.currentOwner = currentOwner;
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }
    public void updateStatus(String status){
        this.status=status;
    }

    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Reader getCurrentOwner() {
        return currentOwner;
    }
    public  void  setCurrentOwner(Reader currentOwner){
        this.currentOwner=currentOwner;
    }

    public String getCategory() {
        return category;
    }
    public void display(){      //Kitap bilgilerini konsola yazdırır. Bilgi görüntüleme amaçlı.
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Kitap{" +
                "kitapId=" + bookId +
                ", ad='" + name + '\'' +
                ", yazar=" + author.getName() +
                ", fiyat=" + price +
                ", durum='" + status + '\'' +
                ", baskı='" + edition + '\'' +
                ", satınAlmaTarihi=" + dateOfPurchase +
                ", kategori='" + category + '\'' +
                '}';
    }
}
