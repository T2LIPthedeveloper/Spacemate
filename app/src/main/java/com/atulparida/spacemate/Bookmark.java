package com.atulparida.spacemate;

public class Bookmark {
    private String id;
    private String title;
    private String description;
    private int imageResource;

    public Bookmark(String id, String title, String description, int imageResource) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
