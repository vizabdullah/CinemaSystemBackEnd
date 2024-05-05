package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Movie;
import SRE.Project.TMS.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>
{
    List<Review> findByMovie(Movie m);

    @Query("select distinct(r.movie) from Review r where r.account.email = :email")
    List<Movie> getUserMovies(@Param("email") String email);

}
