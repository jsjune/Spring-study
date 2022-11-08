package book.object.chapter02;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; //상영할 영화
    private int sequence; //순번
    private LocalDateTime whenScreened; //상영 시작 시간

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovie() {
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        return Reservation.builder()
                .customer(customer)
                .screening(this)
                .fee(calculateFee(audienceCount))
                .audienceCount(audienceCount)
                .build();
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

}
