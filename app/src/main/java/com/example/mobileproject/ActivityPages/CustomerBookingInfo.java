package com.example.mobileproject.ActivityPages;

public class CustomerBookingInfo {
    private String Fname;
    private String Lname;
    private String ID;
    private String BD;
    private String StartDate;
    private String EndDate;

    public CustomerBookingInfo(String fname, String lname, String ID, String BD, String startDate, String endDate) {
        Fname = fname;
        Lname = lname;
        this.ID = ID;
        this.BD = BD;
        StartDate = startDate;
        EndDate = endDate;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBD() {
        return BD;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
