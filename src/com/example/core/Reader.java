package com.example.core;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person{
    private List<Book> borrowedBooks;
    private final int memberId;
    private MemberRecord memberRecord; // Okuyucuya ait üyelik kaydı (Composition)

    public Reader(String name, int memberId, MemberRecord memberRecord) {
        super(name);
        this.borrowedBooks = new ArrayList<>();
        this.memberId = memberId;
        this.memberRecord = memberRecord;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getMemberId() {
        return memberId;
    }

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    public boolean borrowBook(Book book){
        if(memberRecord.getNoBooksIssued()<memberRecord.getMaxBookLimit() && book.getStatus().equals("Available")){
            this.borrowedBooks.add(book);
            book.updateStatus("Borrowed");
            book.setCurrentOwner(this);
            memberRecord.incBookUssued();
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(this.borrowedBooks.contains(book)){
            this.borrowedBooks.remove(book);
            book.updateStatus("Available");
            book.setCurrentOwner(null);
            memberRecord.decBookIssued();
            return  true;
        }
        return false;
    }
    public void showBorrowedBooks(){
        System.out.println("Okuyucu " + getName() + "'nın Aldığı Kitaplar:");
        for (Book book: borrowedBooks){
            System.out.println(book);
        }
    }

    @Override
    public String whoYouAre() {
        return "Okuyucu";
    }

    @Override
    public String toString() {
        return "Okuyucu{" +
                "ad='" + getName() + '\'' +
                ", üyeId=" + memberId +
                ", üyeKaydı=" + memberRecord +
                '}';
    }
}
