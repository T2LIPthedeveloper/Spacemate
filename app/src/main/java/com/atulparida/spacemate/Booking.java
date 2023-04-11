package com.atulparida.spacemate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Booking implements Serializable {
    private String bookingId;
    private Date bookingDate, startTime, endTime;
    private int tableNo, bookedNo, capacity;

    private boolean visibility;

    public Booking(String bookingId, int tableNo, Date startTime, Date endTime, Date bookingDate, int bookedNo, int capacity, boolean visibility) {
        this.bookingId = bookingId;
        this.tableNo = tableNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingDate = bookingDate;
        this.bookedNo = bookedNo;
        this.capacity = capacity;
        this.visibility = visibility;
    }

    public Booking() {
        this.bookingId = null;
        this.tableNo = 0;
        this.startTime = null;
        this.endTime = null;
        this.bookingDate = null;
        this.bookedNo = 0;
        this.capacity = 0;
        this.visibility = false;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
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

    public String parseCapacity() {
        if (capacity == bookedNo)
            return "Fully booked";
        else
            return String.format("Booked %d/%d", bookedNo, capacity);
    }

    public String parseDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(bookingDate);
    }

    public String parseTable() {
        return String.format("Table %d", tableNo);
    }

    public String parseTimings() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        String start = timeFormatter.format(startTime);
        String end = timeFormatter.format(endTime);
        return (start + " to " + end);
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
