package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Account;
import SRE.Project.TMS.Models.Movie;
import SRE.Project.TMS.Models.Review;
import SRE.Project.TMS.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository review_repo;
    @Autowired
    private MovieService movieService;
    @Autowired
    private AccountService accountService;

    List<Review> getMovieReviews(int movieID)
    {
        Movie movie = movieService.getMovie(movieID);
        return review_repo.findByMovie(movie);
    }

    List<Movie> getWatchedMoviesOfUser(String email)
    {
        return review_repo.getUserMovies(email);
    }
    public boolean addReview(String email,int movieId,int star,String comment)
    {
        Account acc = accountService.getAccount(email);
        Movie movie = movieService.getMovie(movieId);

        if(acc == null || movie == null)
        {
            return false;
        }
        else
        {
            review_repo.save(new Review(star,comment,acc,movie));
            return true;
        }
    }
}
