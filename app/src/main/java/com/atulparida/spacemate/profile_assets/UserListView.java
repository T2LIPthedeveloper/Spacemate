package com.atulparida.spacemate.profile_assets;

public class UserListView {
    private int img;
    private String heading;
    private String subtitle;

    UserListView(int img, String heading, String subtitle) {
        this.img = img;
        this.heading = heading;
        this.subtitle = subtitle;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImg() {
        return img;
    }

    public String getHeading() {
        return heading;
    }

    public String getSubtitle() {
        return subtitle;
    }

}
