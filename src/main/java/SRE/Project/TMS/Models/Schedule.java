package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Schedule
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleID;
    private LocalTime time;
    private LocalDate date;

    //RELATION
    @ManyToOne
    private Cinema cinema;
    @ManyToOne
    private Movie movie;
    //


    public Schedule() {
    }

    public Schedule(long scheduleID, LocalTime time, LocalDate date, Cinema cinema, Movie movie) {
        this.scheduleID = scheduleID;
        this.time = time;
        this.date = date;
        this.cinema = cinema;
        this.movie = movie;
    }

    public Schedule(LocalTime time, LocalDate date, Cinema cinema, Movie movie) {
        this.time = time;
        this.date = date;
        this.cinema = cinema;
        this.movie = movie;
    }
}

