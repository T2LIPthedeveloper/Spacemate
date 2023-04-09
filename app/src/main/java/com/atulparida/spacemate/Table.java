package com.atulparida.spacemate;

public class Table {
    private int capacity;
    private int currentlyFilled;

    public Table(int capacity) {
        this.capacity = capacity;
        this.currentlyFilled = 0;
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
}
