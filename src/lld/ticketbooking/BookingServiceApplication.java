package lld.ticketbooking;

import lld.ticketbooking.model.*;
import lld.ticketbooking.model.enums.PaymentStatus;
import lld.ticketbooking.model.enums.PaymentType;
import lld.ticketbooking.model.enums.SeatType;
import lld.ticketbooking.model.enums.ShowSeatStatus;
import lld.ticketbooking.service.BookingService;
import lld.ticketbooking.service.TheaterSearchService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BookingServiceApplication {

    public static void main(String[] args) throws Exception {

        // --------------------------------------------------
        // 1. Prepare movie, show, seats, and theaters
        // --------------------------------------------------

        Movie movie = new Movie(
                "movie-1",
                "Interstellar",
                "Science fiction movie"
        );

        Instant startTime = Instant.now().plusSeconds(3600);
        Instant endTime = startTime.plusSeconds(10800);

        Show show = new Show(
                "show-1",
                movie,
                startTime,
                endTime,
                new ArrayList<>());

        Seat seatA1 = new Seat("A1", SeatType.REGULAR);
        Seat seatA2 = new Seat("A2", SeatType.RECLINER);
        Seat seatA3 = new Seat("A3", SeatType.REGULAR);

        ShowSeat a1 = new ShowSeat(
                "show-seat-1", show, seatA1,
                ShowSeatStatus.AVAILABLE, 200.0);

        ShowSeat a2 = new ShowSeat(
                "show-seat-2", show, seatA2,
                ShowSeatStatus.AVAILABLE, 300.0);

        ShowSeat a3 = new ShowSeat(
                "show-seat-3", show, seatA3,
                ShowSeatStatus.AVAILABLE, 200.0);

        show.addShowSeat(a1);
        show.addShowSeat(a2);
        show.addShowSeat(a3);

        Screen screen = new Screen(
                "screen-1",
                List.of(seatA1, seatA2, seatA3),
                List.of(show));

        Theater mumbaiTheater = new Theater(
                "theater-mumbai",
                "Mumbai Central Cinema",
                "Mumbai",
                List.of(screen));

        Theater puneTheater = new Theater(
                "theater-pune",
                "Pune Cinema",
                "Pune",
                List.of());

        User alice = new User(
                "user-1", "Alice",
                "alice@example.com", "1111111111");

        User bob = new User(
                "user-2", "Bob",
                "bob@example.com", "2222222222");

        User charlie = new User(
                "user-3", "Charlie",
                "charlie@example.com", "3333333333");

        BookingService bookingService = new BookingService();

        // --------------------------------------------------
        // 2. Test search by city
        // --------------------------------------------------

        TheaterSearchService searchService = new TheaterSearchService(
                List.of(mumbaiTheater, puneTheater));

        check(searchService.findTheatersByCity("mumbai").size() == 1,
                "City search should return the Mumbai theater.");

        check(searchService.findMoviesByCity("Mumbai").size() == 1,
                "Movie search should return one distinct movie.");

        check(searchService
                        .findShowsByCityAndMovie("Mumbai", "movie-1")
                        .size() == 1,
                "City and movie search should return the show.");

        check(searchService
                        .findMoviesByCityAndTheater(
                                "Mumbai", "theater-mumbai")
                        .size() == 1,
                "Theater-specific movie search should succeed.");

        check(searchService
                        .findShowsByCityTheaterAndMovie(
                                "Mumbai",
                                "theater-mumbai",
                                "movie-1")
                        .size() == 1,
                "City, theater, and movie search should return the show.");

        check(searchService.findTheatersByCity("Delhi").isEmpty(),
                "Unknown city should return no theaters.");

        System.out.println("TEST 1 PASSED: search by city.");

        // --------------------------------------------------
        // 3. Test concurrent reservation
        // --------------------------------------------------

        List<ShowSeat> selectedSeats = List.of(a1, a2);

        CountDownLatch ready = new CountDownLatch(2);
        CountDownLatch start = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        SeatLock aliceLock;
        SeatLock bobLock;

        try {
            Future<SeatLock> aliceFuture = executor.submit(() ->
                    attemptReservation(
                            bookingService,
                            alice,
                            selectedSeats,
                            ready,
                            start
                    ));

            Future<SeatLock> bobFuture = executor.submit(() ->
                    attemptReservation(
                            bookingService,
                            bob,
                            selectedSeats,
                            ready,
                            start
                    ));

            if (!ready.await(5, TimeUnit.SECONDS)) {
                throw new AssertionError("Threads were not ready in time.");
            }

            // Let both threads attempt the reservation.
            start.countDown();

            aliceLock = aliceFuture.get();
            bobLock = bobFuture.get();

        } finally {
            start.countDown();
            executor.shutdownNow();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }

        int successfulReservations = (aliceLock == null ? 0 : 1) + (bobLock == null ? 0 : 1);

        check(successfulReservations == 1,
                "Exactly one concurrent reservation must succeed.");

        SeatLock winningLock = aliceLock != null ? aliceLock : bobLock;

        check(a1.getShowSeatStatus() == ShowSeatStatus.LOCKED
                        && a2.getShowSeatStatus() == ShowSeatStatus.LOCKED,
                "Both selected seats must be locked.");

        System.out.println("TEST 2 PASSED: exactly one concurrent reservation succeeds.");

        // --------------------------------------------------
        // 4. Test all-or-nothing reservation
        // --------------------------------------------------

        // A2 is locked by the winning reservation, but A3 is available.
        // A request for both must fail without changing A3.
        boolean rejected = false;

        try {
            bookingService.reserveSeats(List.of(a2, a3), charlie);
        } catch (IllegalStateException expected) {
            rejected = true;
        }

        check(rejected, "Request containing an unavailable seat must fail.");

        check(a3.getShowSeatStatus() == ShowSeatStatus.AVAILABLE,
                "A3 must remain available after the failed request.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.LOCKED
                        && a2.getShowSeatStatus() == ShowSeatStatus.LOCKED,
                "The failed request must not change the existing reservation.");

        System.out.println("TEST 3 PASSED: failed grouped reservation changes no seats.");

        // --------------------------------------------------
        // 5. Test cancellation
        // --------------------------------------------------

        check(bookingService.cancelReservation(winningLock),
                "Cancellation of an active reservation should succeed.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.AVAILABLE
                        && a2.getShowSeatStatus() == ShowSeatStatus.AVAILABLE,
                "Cancellation should release all reserved seats.");

        System.out.println("TEST 4 PASSED: cancellation releases all seats.");

        // --------------------------------------------------
        // 6. Test stale cancellation protection
        // --------------------------------------------------

        // Charlie creates a new reservation for those same seats.
        SeatLock charlieLock = bookingService.reserveSeats(selectedSeats, charlie);

        // Repeating Alice/Bob's old cancellation must not release
        // seats now owned by Charlie's reservation.
        check(!bookingService.cancelReservation(winningLock),
                "An already-cancelled reservation must not cancel again.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.LOCKED
                        && a2.getShowSeatStatus() == ShowSeatStatus.LOCKED,
                "Stale cancellation must not release Charlie's seats.");

        System.out.println("TEST 5 PASSED: stale cancellation is harmless.");

        // --------------------------------------------------
        // 7. Test expiry before deadline
        // --------------------------------------------------

        check(!bookingService.expireReservation(charlieLock, Instant.now()),
                "An unexpired reservation must not be released.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.LOCKED,
                "Seats must remain locked before expiry.");

        System.out.println("TEST 6 PASSED: reservation isn't released early.");

        // --------------------------------------------------
        // 8. Test that an unresolved payment prevents expiry
        // --------------------------------------------------

        Payment pendingPayment = new Payment(
                charlieLock.getBooking().getBookingId(),
                null,
                charlieLock.getBooking().getBookingAmount(),
                PaymentType.UPI
        );

        // Payment is PENDING by default.
        charlieLock.getBooking().addPaymentInformation(pendingPayment);

        Instant afterExpiry = charlieLock.getExpireAt().plusSeconds(1);

        check(!bookingService.expireReservation(charlieLock, afterExpiry),
                "An unresolved payment must prevent seat release.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.LOCKED
                        && a2.getShowSeatStatus() == ShowSeatStatus.LOCKED,
                "Seats must remain locked while payment is pending."
        );

        System.out.println("TEST 7 PASSED: pending payment prevents expiry release.");

        // --------------------------------------------------
        // 9. Test expiry after payment is resolved as failed
        // --------------------------------------------------

        pendingPayment.setPaymentStatus(PaymentStatus.FAILED);

        check(bookingService.expireReservation(charlieLock, afterExpiry),
                "Expired reservation should release seats once payment is resolved.");

        check(a1.getShowSeatStatus() == ShowSeatStatus.AVAILABLE
                        && a2.getShowSeatStatus() == ShowSeatStatus.AVAILABLE,
                "Expiry should release every seat in the reservation.");

        System.out.println("TEST 8 PASSED: expired reservation releases all seats.");

        System.out.println("\nALL TESTS PASSED");
    }

    private static SeatLock attemptReservation(BookingService bookingService,
                                               User user, List<ShowSeat> selectedSeats,
                                               CountDownLatch ready,
                                               CountDownLatch start)
            throws InterruptedException {

        ready.countDown();

        if (!start.await(5, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Timed out waiting to start reservation.");
        }

        try {
            SeatLock seatLock = bookingService.reserveSeats(selectedSeats, user);

            System.out.println("SUCCESS: " + user.getName()
                    + " reserved seats. Lock ID: " + seatLock.getSeatLockId());

            return seatLock;

        } catch (IllegalStateException exception) {
            System.out.println("REJECTED: " + user.getName() + " - " + exception.getMessage());

            return null;
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}