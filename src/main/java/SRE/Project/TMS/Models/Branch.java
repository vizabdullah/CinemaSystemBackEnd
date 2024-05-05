package SRE.Project.TMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Branch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long branchID;
    private String area;
    private String city;

    public Branch() {
    }

    public Branch(long branchID, String area, String city) {
        this.branchID = branchID;
        this.area = area;
        this.city = city;
    }
}
