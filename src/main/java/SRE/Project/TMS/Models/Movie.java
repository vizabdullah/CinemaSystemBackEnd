package SRE.Project.TMS.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieID;
    private String movieName;
    private String genre;
    private String link;
    private float duration;
    private String mainChar;
    private String description;

    public Movie() {
    }

    public Movie(String movieName, String genre, String link, float duration, String mainChar, String description) {
        this.movieName = movieName;
        this.genre = genre;
        this.link = link;
        this.duration = duration;
        this.mainChar = mainChar;
        this.description = description;
    }

    public Movie(int movieID, String movieName, String genre, String link, float duration, String mainChar, String description) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.genre = genre;
        this.link = link;
        this.duration = duration;
        this.mainChar = mainChar;
        this.description = description;
    }
}
