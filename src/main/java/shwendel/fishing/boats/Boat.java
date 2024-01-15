package shwendel.fishing.boats;

import shwendel.fishing.boats.theme.BoatTheme;
import shwendel.fishing.user.User;

public class Boat {

    private User owner;
    private String name; // Name displayed on side of boat
    private BoatSize size;
    private BoatTheme theme;

    public Boat(User owner, String name, BoatSize size, BoatTheme theme) {
        this.owner = owner;
        this.name = name;
        this.size = size;
        this.theme = theme;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoatSize getSize() {
        return size;
    }

    public void setSize(BoatSize size) {
        this.size = size;
    }

    public BoatTheme getTheme() {
        return theme;
    }

    public void setTheme(BoatTheme theme) {
        this.theme = theme;
    }

}
