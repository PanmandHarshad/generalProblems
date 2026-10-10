package lld.ticketbooking.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private final String id;
    private final Movie movie;
    private final Instant startTime;
    private final Instant endTime;
    private List<ShowSeat> showSeatList;

    public Show(String id, Movie movie, Instant startTime, Instant endTime, List<ShowSeat> showSeatList) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showSeatList = new ArrayList<>(showSeatList);
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void addShowSeat(ShowSeat showSeat) {
        if (showSeat == null || showSeat.getShow() != this) {
            throw new IllegalArgumentException("ShowSeat must belong to this show.");
        }

        showSeatList.add(showSeat);
    }

    public List<ShowSeat> getShowSeatList() {
        return List.copyOf(showSeatList);
    }
}
