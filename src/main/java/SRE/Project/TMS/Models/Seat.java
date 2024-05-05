package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatID;
    private char seatRow;
    private char seatColumn;

    // RELATIONS
    @ManyToOne
    private Cinema cinema;
    //


    public Seat() {
    }

    public Seat(char seatRow, char seatColumn, Cinema cinema) {

        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.cinema = cinema;
    }
}
