package com.example.core;

public class Faculty extends MemberRecord{
    private String facultyId;
    private String department;
    private String position;

    public Faculty(int memberId, String name, String address,
                   String phoneNo, String facultyId, String department, String position) {
        super(memberId, "Fakülte Üyesi", name, address, phoneNo);
        this.facultyId = facultyId;
        this.department = department;
        this.position = position;
    }

    public String facultyId() {
        return facultyId;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public double calculateFee() {
        return 50;
    }

    @Override
    public String toString() {
        return "Fakülte{" + super.toString() +
                ", fakülteId='" + facultyId + '\'' +
                ", bölüm='" + department + '\'' +
                ", pozisyon='" + position + '\'' +
                '}';
    }
}
