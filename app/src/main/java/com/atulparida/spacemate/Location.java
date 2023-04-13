package com.atulparida.spacemate;

import java.io.Serializable;
//import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Date;

public class Location implements Serializable {
    private int imgResource;
    private String name;
    private boolean isFavourite;
    private int max_capacity, currentlyFilled;

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    private List<Table> tableList;
    //The existence of bookingList inside the location class is temporary. By right, it should be part of the Table class.
    private List<Booking> bookingList;

    public String getName() {
        return name;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public int getCurrentlyFilled() {
        return currentlyFilled;
    }

    public Location(String name, List<Table> tableList, int imgResource) {
        this.name = name;
        this.tableList = tableList;
        this.bookingList = null;
        this.imgResource = imgResource;

        this.currentlyFilled = 0;
        this.max_capacity = 0;
        this.isFavourite = false;
    }

    private void computeValues() {
        for (Table t : tableList) {
            max_capacity += t.getCapacity();
            if (currentlyFilled <= max_capacity)
                currentlyFilled += t.getCurrentlyFilled();
        }
        // https://stackoverflow.com/questions/6850874/how-to-create-a-java-date-object-of-midnight-today-and-midnight-tomorrow/36784346#36784346
        ZoneId zoneId = ZoneId.of("Singapore");
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        ZonedDateTime todayStart = now.toLocalDate().atStartOfDay(zoneId);
        ZonedDateTime onlyTimeNow = now.withDayOfYear(1).withYear(1970);
        Date startOfToday = Date.from(todayStart.toInstant());
        Date timeNow = Date.from(onlyTimeNow.toInstant());
        if (bookingList.isEmpty()){}
        else{
        for (Booking b : bookingList) {
            if ((b.getBookingDate().compareTo(startOfToday) == 0)
                    && (b.getStartTime().compareTo(timeNow) < 0)
                    && (b.getEndTime().compareTo(timeNow) > 0)) {
                currentlyFilled += b.getBookedNo();
            }
        }
        }
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getParsedCapacity() {
        System.out.println("parsing capacity");
        if (max_capacity == currentlyFilled) {
            return "Location full";
        }
        else {
            return String.format("%d/%d", currentlyFilled, max_capacity);
        }
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
        computeValues();
    }
}
