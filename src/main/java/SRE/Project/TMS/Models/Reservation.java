package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;


    // Relations
    @ManyToOne
    private Schedule schedule;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Guest guest;

    public Reservation() {
    }

    public Reservation( String recordID, Schedule schedule, Seat seat, Account account, Guest guest)
    {
        this.schedule = schedule;
        this.seat = seat;
        this.account = account;
        this.guest = guest;
    }
}
