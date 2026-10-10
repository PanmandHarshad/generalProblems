package lld.ticketbooking.service;

import lld.ticketbooking.model.Movie;
import lld.ticketbooking.model.Show;
import lld.ticketbooking.model.Theater;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TheaterSearchService {

    private final List<Theater> theaters;

    public TheaterSearchService(List<Theater> theaters) {
        this.theaters = List.copyOf(theaters);
    }

    public List<Theater> findTheatersByCity(String city) {
        if (city == null || city.isBlank()) {
            return List.of();
        }

        return theaters.stream()
                .filter(theater -> theater.getCity() != null)
                .filter(theater ->
                        theater.getCity().equalsIgnoreCase(city.trim()))
                .toList();
    }

    // City -> Movies
    public List<Movie> findMoviesByCity(String city) {
        return distinctMovies(
                showsIn(findTheatersByCity(city))
                        .map(Show::getMovie));
    }

    // City -> Movie -> Shows
    public List<Show> findShowsByCityAndMovie(String city, String movieId) {
        if (movieId == null || movieId.isBlank()) {
            return List.of();
        }

        return showsIn(findTheatersByCity(city))
                .filter(show -> show.getMovie() != null)
                .filter(show ->
                        movieId.equals(show.getMovie().getId()))
                .toList();
    }

    // City -> Theater -> Movies
    public List<Movie> findMoviesByCityAndTheater(String city, String theaterId) {
        List<Theater> matchingTheaters =
                findTheatersByCity(city).stream()
                        .filter(theater ->
                                Objects.equals(
                                        theater.getId(), theaterId))
                        .toList();

        return distinctMovies(showsIn(matchingTheaters)
                .map(Show::getMovie));
    }

    // City -> Theater -> Movie -> Shows
    public List<Show> findShowsByCityTheaterAndMovie(String city, String theaterId, String movieId) {
        if (theaterId == null || movieId == null) {
            return List.of();
        }

        List<Theater> matchingTheaters =
                findTheatersByCity(city).stream()
                        .filter(theater ->
                                Objects.equals(
                                        theater.getId(), theaterId))
                        .toList();

        return showsIn(matchingTheaters)
                .filter(show -> show.getMovie() != null)
                .filter(show ->
                        movieId.equals(show.getMovie().getId()))
                .toList();
    }

    private Stream<Show> showsIn(List<Theater> selectedTheaters) {
        return selectedTheaters.stream()
                .flatMap(theater -> theater.getScreenList().stream())
                .flatMap(screen -> screen.getShowList().stream());
    }

    private List<Movie> distinctMovies(Stream<Movie> movies) {
        Map<String, Movie> uniqueMovies = movies
                .filter(Objects::nonNull)
                .filter(movie -> movie.getId() != null)
                .collect(Collectors.toMap(
                        Movie::getId,
                        Function.identity(),
                        (first, duplicate) -> first,
                        LinkedHashMap::new)
                );

        return List.copyOf(uniqueMovies.values());
    }
}