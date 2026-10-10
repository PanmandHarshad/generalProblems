package lld.ticketbooking.model;

import java.util.List;

public class Theater {
    private final String id;
    private final String name;
    private final String city;
    private final List<Screen> screenList;

    public Theater(String id, String name, String city, List<Screen> screenList) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screenList = List.copyOf(screenList);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", screenList=" + screenList +
                '}';
    }
}
