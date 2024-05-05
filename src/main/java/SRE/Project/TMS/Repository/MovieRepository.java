package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie,Integer>
{
    @Query("Select m From Movie m where m.movieID = (Select MAX(n.movieID) from Movie n)")
    public Movie getLatestMovie();

    Movie findByMovieName(String name);
}
