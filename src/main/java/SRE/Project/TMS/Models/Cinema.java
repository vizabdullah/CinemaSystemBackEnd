package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cinema
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CinemaID;
    private String CinemaName;
    private int NoOfSeats;

    // Relations
    @ManyToOne
    private Branch branch;
    @ManyToOne
    private TypeDetails type;
    //


    public Cinema() {
    }

    public Cinema(long cinemaID, String cinemaName, int noOfSeats, Branch branch, TypeDetails type) {
        CinemaID = cinemaID;
        CinemaName = cinemaName;
        NoOfSeats = noOfSeats;
        this.branch = branch;
        this.type = type;
    }
}
