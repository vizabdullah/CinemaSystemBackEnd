package SRE.Project.TMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    private int stars;
    private String text;

    //RELATIONS
    @ManyToOne
    private Account account;
    @ManyToOne
    private Movie movie;
    //

    public Review() {
    }

    public Review(int stars, String text, Account account, Movie movie) {
        this.stars = stars;
        this.text = text;
        this.account = account;
        this.movie = movie;
    }
}
