package com.example.core;
import java.time.LocalDate;

public abstract class MemberRecord {
    private int memberId;
    private String type;//Student - Faculty
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;


    public MemberRecord(int memberId, String type, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = LocalDate.now();
        this.noBooksIssued = 0;
        this.maxBookLimit = 5;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void incBookUssued(){
        this.noBooksIssued++;
    }
    public void decBookIssued(){
        this.noBooksIssued--;
    }
    public abstract double calculateFee();

    @Override
    public String toString() {
        return "Üye Kaydı{" +
                "üyeId=" + memberId +
                ", tip='" + type + '\'' +
                ", üyelikTarihi=" + dateOfMembership +
                ", alınanKitapSayısı=" + noBooksIssued +
                ", kitapLimiti=" + maxBookLimit +
                ", ad='" + name + '\'' +
                ", adres='" + address + '\'' +
                ", telefonNumarası='" + phoneNo + '\'' +
                '}';
    }
}
