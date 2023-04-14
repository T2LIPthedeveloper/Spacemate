package com.atulparida.spacemate;

import android.media.Image;
import android.net.Uri;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

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
        this.imgResource = imgResource;

        this.currentlyFilled = 0;
        this.max_capacity = 0;
        this.isFavourite = false;
        computeValues();
    }

    private void computeValues() {
        for (Table t: tableList) {
            max_capacity += t.getCapacity();
            if (currentlyFilled <= max_capacity)
                currentlyFilled += t.getCurrentlyFilled();
        }
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getParsedCapacity() {
        if (max_capacity == currentlyFilled) {
            return "Location full";
        }
        else {
            return String.format("%d/%d", currentlyFilled, max_capacity);
        }
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
