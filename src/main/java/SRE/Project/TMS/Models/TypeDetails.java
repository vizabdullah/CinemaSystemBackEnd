package SRE.Project.TMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TypeDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeID;
    private String type;
    private int price;

    public TypeDetails() {
    }

    public TypeDetails(long typeID, String type, int price) {
        this.typeID = typeID;
        this.type = type;
        this.price = price;
    }

    public TypeDetails(String type, int price) {
        this.type = type;
        this.price = price;
    }
}
