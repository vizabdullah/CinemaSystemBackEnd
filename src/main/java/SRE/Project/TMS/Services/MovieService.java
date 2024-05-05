package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Movie;
import SRE.Project.TMS.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    private MovieRepository movie_repo;

    public Movie getLatestMovie()
    {
        return movie_repo.getLatestMovie();
    }

    public boolean addNewMovie(Movie m)
    {
        movie_repo.save(m);
        return true;
    }
    public boolean editMovie(Movie mov, int oldMovID)
    {
        Movie existing_movie = movie_repo.findById(oldMovID).orElse(null);

        if(existing_movie == null)
        {
            return false;
        }
        else
        {
            existing_movie.setMovieName(mov.getMovieName());
            existing_movie.setLink(mov.getLink());
            existing_movie.setGenre(mov.getGenre());
            existing_movie.setDuration(mov.getDuration());
            existing_movie.setMainChar(mov.getMainChar());
            existing_movie.setDescription(mov.getDescription());
            movie_repo.save(existing_movie);
            return true;
        }
    }
    public Movie getMovie(int id)
    {
        return movie_repo.findById(id).orElse(null);
    }

    public List<Movie> getMovies()
    {
        return movie_repo.findAll();
    }

    public int getMovieId(String name)
    {
        Movie m = movie_repo.findByMovieName(name);
        return m.getMovieID();
    }
    public boolean movieExists(int movieId)
    {
        return movie_repo.existsById(movieId);
    }
}
