package com.example.operations;

import com.example.core.Book;
import com.example.core.Invoice;
import com.example.core.Reader;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Librarian {
    private String name;
    private String password;
    private Library library;
    private Map<Integer, Invoice> invoices;
    private int nextInvoiceId=1;

    public Librarian(String name, String password, Library library) {
        this.name = name;
        this.password = password;
        this.library = library;
        this.invoices = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }

    public Invoice createBill(Reader reader, Book book) {
        Invoice invoice = new Invoice(nextInvoiceId++, reader, book, 10.0); // Sabit 10 TL ödünç alma ücreti
        return invoice;
    }

    public boolean issueBook(int bookId, int memberId){
        Reader reader=library.getReaderById(memberId);
        if(reader==null){
            System.out.println("Okuyucu Bulunamadı!");
            return false;
        }
        Book book=library.getBookById(bookId);
        if(book==null){
            System.out.println("Kitap Bulunmadı!");
            return false;
        }
        if (library.lendBook(bookId, reader)) {
            Invoice invoice = createBill(reader, book);
            invoices.put(invoice.getInvoiceId(), invoice);
            return true;
        } else {
            System.out.println("Kitap ödünç alınamadı. Limit aşımı veya kitap mevcut değil.");
            return false;
        }

    }
    private Invoice getInvoiceForBookAndReader(int bookId, int memberId){
        for (Invoice invoice:invoices.values()){
            if(invoice.getBook().getBookId()==bookId && invoice.getReader().getMemberRecord().getMemberId()==memberId &&
            invoice.getReturnDate()==null){
                return invoice;
            }
        }
        return  null;
    }

    public double calculateFine(Invoice invoice) {
        if(invoice.getReturnDate() !=null){
            long daysLate= ChronoUnit.DAYS.between(invoice.getIssueDate().plusDays(7),invoice.getReturnDate());
            // 7 gün ödünç süresi
            if(daysLate>0){
                return daysLate*10; //Gün başına 10 TL ceza
            }
        }
        return 0;
    }


    public boolean returnBook(int bookId, int memberId) {
        Reader reader = library.getReaderById(memberId);
        if (reader == null) {
            System.out.println("Okuyucu bulunamadı.");
            return false;
        }
        if (library.takeBackBook(bookId, reader)) {
            Invoice invoice = getInvoiceForBookAndReader(bookId, memberId);
            if (invoice != null) {
                invoice.setReturnDate(LocalDate.now());
                double fine = calculateFine(invoice);
                if (fine > 0) {
                    System.out.println("Gecikme cezası: " + fine + " TL");
                }
            }
            return true;
        } else {
            System.out.println("Kitap iade edilemedi.");
            return false;
        }
    }


    public Map<Integer, Invoice> getInvoices() {
        return invoices;
    }

}
