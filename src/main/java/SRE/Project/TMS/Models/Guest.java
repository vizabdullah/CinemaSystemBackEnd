package SRE.Project.TMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Guest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestID;
    private String guestEmail;
    private String guestName;
    private LocalDate guestDOB;
    private int guestPhone;

    public Guest() {
    }

    public Guest(String guestEmail, String guestName, LocalDate guestDOB, int guestPhone) {
        this.guestEmail = guestEmail;
        this.guestName = guestName;
        this.guestDOB = guestDOB;
        this.guestPhone = guestPhone;
    }
}
