package com.example.core;

import java.util.List;

public class Student extends MemberRecord{
    private String studentId;
    private String department;

    public Student(int memberId,String name, String address, String phoneNo, String studentId, String department) {
        super(memberId, "Öğrenci", name, address, phoneNo);
        this.studentId = studentId;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public double calculateFee() {
        return 0;// Öğrencilerden ücret alınmıyor
    }

    @Override
    public String toString() {
        return "Öğrenci{" + super.toString() +
                ", öğrenciId='" + studentId + '\'' +
                ", bölüm='" + department + '\'' +
                '}';
    }
}
