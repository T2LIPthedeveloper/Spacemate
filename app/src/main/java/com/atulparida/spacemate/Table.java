package com.atulparida.spacemate;

import java.io.Serializable;

public class Table implements Serializable {
    private int capacity;
    private int currentlyFilled;

    private String tableName;

    public Table(String tableName, int capacity) {
        this.capacity = capacity;
        this.currentlyFilled = 0;
        this.tableName = tableName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentlyFilled() {
        return currentlyFilled;
    }

    public void setCurrentlyFilled(int currentlyFilled) {
        if (currentlyFilled <= capacity)
            this.currentlyFilled = currentlyFilled;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
