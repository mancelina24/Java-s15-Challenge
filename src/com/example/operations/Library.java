package com.example.operations;

import com.example.core.Author;
import com.example.core.Book;
import com.example.core.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Reader> readers;
    private Map<String, List<Book>> booksByCategory;
    private Map<Author, List<Book>> booksByAuthor;
    private int nextBookId = 1;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.booksByCategory = new HashMap<>();
        this.booksByAuthor = new HashMap<>();
        }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }
    public void addBook(Book book){
        this.books.add(book);
        //Kategorye göre kitabı ilgili kategori listesine ekle
        booksByCategory.computeIfAbsent(book.getCategory(),k -> new ArrayList<>()).add(book);
        //Yazara göre kitabı ilgili yazarın listesine ekle
        booksByAuthor.computeIfAbsent(book.getAuthor(), k -> new ArrayList<>()).add(book);
    }
    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Eğer kitap bulunamazsa null döndür
    }
    public void removeBook(int bookId) {
        Book bookToRemove = getBookById(bookId);
        if (bookToRemove != null) {
            books.remove(bookToRemove); // Kitabı genel listeden kaldır

            // Kategorisine göre haritadan da kaldır
            booksByCategory.get(bookToRemove.getCategory()).remove(bookToRemove);

            // Yazarına göre haritadan da kaldır
            booksByAuthor.get(bookToRemove.getAuthor()).remove(bookToRemove);

            System.out.println("Kitap başarıyla kaldırıldı: " + bookToRemove.getName());
        } else {
            System.out.println("Kitap bulunamadı!");
        }
    }
    public boolean lendBook(int bookId, Reader reader){
        Book book=getBookById(bookId);
        if(book !=null){
            return  reader.borrowBook(book);
        }
        return false;
    }
    public boolean takeBackBook(int bookId, Reader reader){
        Book book=getBookById(bookId);
        if (book != null) {
            return reader.returnBook(book);
        }
        return false;
    }

    public List<Book> getBooksByName(String bookName){
        return books
                .stream()
                .filter(book-> book.getName().toLowerCase()
                .contains(bookName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(Author author) {
        return booksByAuthor.getOrDefault(author, new ArrayList<>());
    }
    public List<Book> getBooksByCategory(String category) {
        return booksByCategory.getOrDefault(category, new ArrayList<>());
    }
    public void registerReader(Reader reader){
        this.readers.add(reader);
    }
    public Reader getReaderById(int memberId){
        for(Reader reader:readers){
            if(reader.getMemberRecord().getMemberId()==memberId){
                return reader;
            }
        }
        return null;
    }
    public int getNextBookId(){
        return nextBookId++;
    }
}
