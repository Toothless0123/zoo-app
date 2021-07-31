package com.example.zooappframework;
import android.graphics.drawable.Drawable;

import java.io.File;

public class Animal {
    private String Name;
    private String Description;
    private String myDrawable;
    private int ID;
    private String Habitat;

    public Animal(String name, String desc, String drawableName, int id) {
        Name = name;
        Description = desc;
        myDrawable = drawableName;
        ID = id;
    }

    public Animal(String name, String desc, int id) {
        Name = name;
        Description = desc;
        ID = id;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public String getDesc() {
        return this.Description;
    }

    public String getIM() {
        return this.myDrawable;
    }
}