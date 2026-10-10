package lld.ticketbooking.model;

import java.util.List;

public class Screen {
    private final String id;
    private final List<Seat> seatList;
    private final List<Show> showList;

    public Screen(String id, List<Seat> seatList, List<Show> showList) {
        this.id = id;
        this.seatList = List.copyOf(seatList);
        this.showList = List.copyOf(showList);
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public List<Show> getShowList() {
        return showList;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id='" + id + '\'' +
                ", seatList=" + seatList +
                ", showList=" + showList +
                '}';
    }
}
