package com.atulparida.spacemate;

import android.media.Image;
import android.net.Uri;

import java.util.List;

public class Location {
    private Uri uri;
    private String name;
    private boolean isFavourite;
    private int max_capacity, currentlyFilled;
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

    public Location(String name, List<Table> tableList, Uri uri) {
        this.name = name;
        this.tableList = tableList;
        this.uri = uri;

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
}
