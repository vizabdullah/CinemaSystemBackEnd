package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Movie;
import SRE.Project.TMS.Services.MovieService;
import SRE.Project.TMS.Services.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("api/review")
public class ReviewController
{
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/Addreview")
    public void addReview(@SessionAttribute("Name") String name,
                            @RequestParam("Star") int star,
                            @RequestParam("comment") String comment,
                            @RequestParam("movie") String movieName,
                            HttpSession session)
    {

        reviewService.addReview(name,movieService.getMovieId(movieName),star,comment);
    }
}
