package com.example.core;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person{
    private List<Book> books;//yazarın yazdığı kitapların listesi

    public Author(String name) {
        super(name);
        this.books =new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public Book newBook(int bookId, String name, double price, String edition, String category) {
        Book book = new Book(bookId, name, this, price, "Available", edition, LocalDate.now(), null, category);
        addBook(book);
        return book;
    }

    public void showBooks(){
        System.out.println("Yazar "+getName()+" ın Kitapları: ");
        for (Book book:books){
            System.out.println(book);
        }
    }

    @Override
    public String whoYouAre() {
        return "Yazar";
    }

    @Override
    public String toString() {
        return "Yazar{" +
                "isim=" + getName() +
                '}';
    }
}
