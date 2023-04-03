package com.atulparida.spacemate;

import java.util.Date;

public class Booking {
    private String bookingId, startTime, endTime;
    private Date bookingDate;
    private int tableNo, bookedNo, capacity;

    public Booking(String bookingId, int tableNo, String startTime, String endTime, Date bookingDate, int bookedNo, int capacity) {
        this.bookingId = bookingId;
        this.tableNo = tableNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingDate = bookingDate;
        this.bookedNo = bookedNo;
        this.capacity = capacity;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public int getBookedNo() {
        return bookedNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookedNo(int bookedNo) {
        this.bookedNo = bookedNo;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", tableNo='" + tableNo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookedNo='" + bookedNo + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
