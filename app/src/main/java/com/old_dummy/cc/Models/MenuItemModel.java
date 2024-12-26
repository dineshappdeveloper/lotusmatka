package com.old_dummy.cc.Models;

public class MenuItemModel {

    private String title;
    private int icon;

    public MenuItemModel(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}
