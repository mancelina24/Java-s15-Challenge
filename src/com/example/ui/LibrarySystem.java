package com.example.ui;

import com.example.core.*;
import com.example.operations.Librarian;
import com.example.operations.Library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {
    private Library library;
    private Librarian librarian;
    private Scanner scanner;
    private Map<Integer, MemberRecord> memberRecords;
    private int nextMemberId=1;

    public LibrarySystem() {
        this.library = new Library();
        this.librarian = new Librarian("admin","password", library);
        this.scanner = new Scanner(System.in);
        this.memberRecords = new HashMap<>();
    }

    // **************   Kitap Yönetimi UI   ***************

    private void addBookUI() {
        System.out.println("\n--- Kitap Ekle ---");
        System.out.print("Kitap Adı: ");
        String name = scanner.nextLine();
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName); // Basit yazar oluşturma, iyileştirilebilir
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();
        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        Book book = author.newBook(library.getNextBookId(), name, price, edition, category);
        library.addBook(book);
        System.out.println("Kitap eklendi: " + book);
    }

    private void removeBookUI() {
        System.out.println("\n--- Kitap Sil ---");
        System.out.print("Silinecek Kitap ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        library.removeBook(bookId);
        System.out.println("Kitap silindi.");
    }

    private void listBooksUI() {
        System.out.println("\n--- Kitap Listesi ---");
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap bulunamadı.");
        } else {
            for (Book book : books) {
                book.display();
            }
        }
    }
// **************   Kitap Yönetimi MENU   ***************

    private void bookManagementMenu(){
        while(true){
            System.out.println("\n--- Kitap Yönetimi ---");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Sil");
            System.out.println("3. Kitapları Listele");
            System.out.println("4. Ana Menüye Dön");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    addBookUI();
                    break;
                case 2:
                    removeBookUI();
                    break;
                case 3:
                    listBooksUI();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }

    }

// *******************     Okuyucu Yönetimi MENU **********************

    private void readerManagementMenu() {
        while (true) {
            System.out.println("\n--- Okuyucu Yönetimi ---");
            System.out.println("1. Öğrenci Kaydı");
            System.out.println("2. Fakülte Üyesi Kaydı");
            System.out.println("3. Okuyucuları Listele");
            System.out.println("4. Ana Menüye Dön");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerStudentUI();
                    break;
                case 2:
                    registerFacultyUI();
                    break;
                case 3:
                    listReadersUI();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

// *******************     Okuyucu Yönetimi UI **********************

    private Student createStudent(String name, String address, String phoneNo, String studentId, String department) {
        Student student = new Student(nextMemberId++, name, address, phoneNo, studentId, department);
        memberRecords.put(student.getMemberId(), student);
        return student;
    }
    private void registerStudentUI(){
        System.out.println("\n--- Öğrenci Kaydı ---");
        System.out.print("Öğrenci Adı: ");
        String name=scanner.nextLine();
        System.out.println("Adres: ");
        String address= scanner.nextLine();
        System.out.println("Telefon Numarası: ");
        String phoneNo = scanner.nextLine();
        System.out.print("Öğrenci ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Bölüm: ");
        String department = scanner.nextLine();

        Student studentRecord=createStudent(name,address,phoneNo,studentId,department);
        Reader reader=new Reader(name, studentRecord.getMemberId(),studentRecord);
        library.registerReader(reader);
        System.out.println("Öğrenci kaydı oluşturuldu: " + reader);
    }

    private Faculty createFaculty(String name, String address, String phoneNo, String facultyId, String department, String position) {
        Faculty faculty = new Faculty(nextMemberId++, name, address, phoneNo, facultyId, department, position);
        memberRecords.put(faculty.getMemberId(), faculty);
        return faculty;
    }

    private void registerFacultyUI(){
        System.out.println("\n--- Fakülte Üyesi Kaydı ---");
        System.out.print("Fakülte Üyesi Adı: ");
        String name = scanner.nextLine();
        System.out.print("Adres: ");
        String address = scanner.nextLine();
        System.out.print("Telefon Numarası: ");
        String phoneNo = scanner.nextLine();
        System.out.print("Fakülte ID: ");
        String facultyId = scanner.nextLine();
        System.out.print("Bölüm: ");
        String department = scanner.nextLine();
        System.out.print("Pozisyon: ");
        String position = scanner.nextLine();

        Faculty facultyRecord = createFaculty(name, address, phoneNo, facultyId, department, position);
        Reader reader = new Reader(name, facultyRecord.getMemberId(), facultyRecord);
        library.registerReader(reader);
        System.out.println("Fakülte üyesi kaydı oluşturuldu: " + reader);
    }

    private void listReadersUI() {
        System.out.println("\n--- Okuyucu Listesi ---");
        List<Reader> readers = library.getReaders();
        if (readers.isEmpty()) {
            System.out.println("Kütüphanede okuyucu bulunamadı.");
        } else {
            for (Reader reader : readers) {
                System.out.println(reader);
            }
        }
    }

    //**********       Ödünç/İade İşlemleri UI ********************
    private void borrowBookUI() {
        System.out.println("\n--- Kitap Ödünç Ver ---");
        System.out.print("Kitap ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Üye ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (librarian.issueBook(bookId, memberId)) {
            System.out.println("Kitap ödünç verildi.");
        }
    }

    private void returnBookUI() {
        System.out.println("\n--- Kitap İade Al ---");
        System.out.print("Kitap ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Üye ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (librarian.returnBook(bookId, memberId)) {
            System.out.println("Kitap iade alındı.");
        }
    }


    //**********       Ödünç/İade İşlemleri MENU ********************

    private void borrowReturnMenu() {
        while (true) {
            System.out.println("\n--- Ödünç/İade İşlemleri ---");
            System.out.println("1. Kitap Ödünç Ver");
            System.out.println("2. Kitap İade Al");
            System.out.println("3. Ana Menüye Dön");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    borrowBookUI();
                    break;
                case 2:
                    returnBookUI();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

    //**********    Arama İşlemleri UI ********************

    private void searchBookByNameUI() {
        System.out.println("\n--- Kitap Adına Göre Ara ---");
        System.out.print("Kitap Adı: ");
        String bookName = scanner.nextLine();

        List<Book> books = library.getBooksByName(bookName);
        if (books.isEmpty()) {
            System.out.println("Kitap bulunamadı.");
        } else {
            System.out.println("Bulunan Kitaplar:");
            for (Book book : books) {
                book.display();
            }
        }
    }

    private void searchBookByAuthorUI() {
        System.out.println("\n--- Yazar Adına Göre Ara ---");
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName); // Basit yazar oluşturma, iyileştirilebilir

        List<Book> books = library.getBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("Yazara ait kitap bulunamadı.");
        } else {
            System.out.println("Bulunan Kitaplar:");
            for (Book book : books) {
                book.display();
            }
        }
    }

    private void searchBookByCategoryUI() {
        System.out.println("\n--- Kategoriye Göre Ara ---");
        System.out.print("Kategori Adı: ");
        String category = scanner.nextLine();

        List<Book> books = library.getBooksByCategory(category);
        if (books.isEmpty()) {
            System.out.println("Kategoride kitap bulunamadı.");
        } else {
            System.out.println("Bulunan Kitaplar:");
            for (Book book : books) {
                book.display();
            }
        }
    }


    //**********       Arama İşlemleri MENU ********************

    private void searchMenu() {
        while (true) {
            System.out.println("\n--- Arama İşlemleri ---");
            System.out.println("1. Kitap Adına Göre Ara");
            System.out.println("2. Yazar Adına Göre Ara");
            System.out.println("3. Kategoriye Göre Ara");
            System.out.println("4. Ana Menüye Dön");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchBookByNameUI();
                    break;
                case 2:
                    searchBookByAuthorUI();
                    break;
                case 3:
                    searchBookByCategoryUI();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

    //*************     Fatura işlemleri MENU     *************

    private void listInvoicesMenu() {
        System.out.println("\n--- Faturalar ---");
        Map<Integer, Invoice> invoices = librarian.getInvoices();
        if (invoices.isEmpty()) {
            System.out.println("Fatura bulunamadı.");
        } else {
            for (Invoice invoice : invoices.values()) {
                invoice.display();
            }
        }
    }

//*************     displayMainMenu     *************


    private void displayMainMenu(){
        while(true){
            System.out.println("\n--- Ana Menü ---");
            System.out.println("1. Kitap Yönetimi");
            System.out.println("2. Okuyucu Yönetimi");
            System.out.println("3. Ödünç/İade İşlemleri");
            System.out.println("4. Arama İşlemleri");
            System.out.println("5. Faturaları Listele");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookManagementMenu();
                    break;
                case 2:
                    readerManagementMenu();
                    break;
                case 3:
                    borrowReturnMenu();
                    break;
                case 4:
                    searchMenu();
                    break;
                case 5:
                    listInvoicesMenu();
                    break;
                case 6:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
}



    private void initializeData(){
        Author author1=new Author("Agatha Cristie");
        Author author2=new Author("Stephen King");

        library.addBook(author1.newBook(library.getNextBookId(),"Doğu Ekspresinde Cinayet", 450.0,"1.Baskı","Polisiye"));
        library.addBook(author1.newBook(library.getNextBookId(),"Cinayet Alfabesi", 430.0,"1.Baskı","Polisiye"));
        library.addBook(author2.newBook(library.getNextBookId(), "Hayvan Mezarlığı", 380.0, "2. Baskı", "Korku"));
        Student student1 = createStudent("Ayşe Demir", "Ankara", "1234567890", "std123", "Bilgisayar Mühendisliği");
        Faculty faculty1 = createFaculty("Prof. Dr. Mehmet Kaya", "İstanbul", "0987654321", "fac456", "Mühendislik Fakültesi", "Profesör");
        library.registerReader(new Reader("Ayşe Demir", student1.getMemberId(), student1));
        library.registerReader(new Reader("Mehmet Kaya", faculty1.getMemberId(), faculty1));
    }

    public void start(){
        System.out.println("Kütüphane Sistemine Hoş Geldiniz!");
        displayMainMenu();
    }
}
