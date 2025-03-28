package com.example.core;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person{
    private List<Book> books;
    private final int memberId;
    private MemberRecord memberRecord;

    public Reader(String name, int memberId, MemberRecord memberRecord) {
        super(name);
        this.books = new ArrayList<>();
        this.memberId = memberId;
        this.memberRecord = memberRecord;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getMemberId() {
        return memberId;
    }

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    public boolean borrowBook(Book book){
        if(memberRecord.getNoBooksIssued()<memberRecord.getMaxBookLimit() && book.getStatus().equals("Available")){
            this.books.add(book);
            book.updateStatus("Borrowed");
            book.setCurrentOwner(this);
            memberRecord.incBookUssued();
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.updateStatus("Available");
            book.setCurrentOwner(null);
            memberRecord.decBookIssued();
            return  true;
        }
        return false;
    }
    public void showBooks(){
        System.out.println("Okuyucu " + getName() + "'nın Aldığı Kitaplar:");
        for (Book book: books){
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
