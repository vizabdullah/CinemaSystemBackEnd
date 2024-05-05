package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class History
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long historyId;
    private String RecordID;
    private int Price;

    //Relations
    @ManyToOne
    private Schedule schedule;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Guest guest;
    //Relations

}
