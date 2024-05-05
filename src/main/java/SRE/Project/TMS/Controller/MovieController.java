package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Movie;
import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Services.MovieService;
import SRE.Project.TMS.Services.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/movie")
public class MovieController
{
    @Autowired
    private MovieService movieService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/getMovie")
    public void getMovies(@RequestParam(value = "Cities", required = false) String cities,
                            @RequestParam(value = "Branches", required = false) String branches,
                            @RequestParam(value = "Cinemas", required = false) String cinemas,
                            @RequestParam(value = "Movies", required = false) String movies,
                            @RequestParam(value = "Times", required = false) String times,
                            @RequestParam(value = "Dates", required = false) String dates,
                            @RequestParam(value = "Prices", required = false) String prices,
                            @RequestParam(value = "Genres", required = false) String genres,
                            Model model
                            )
    {

        String[][] arr = new String[8][2];

        arr[0][0] = "b.City";
        arr[1][0] = "c.BranchID";
        arr[2][0] = "c.CinemaName";
        arr[3][0] = "s.MovieID";
        arr[4][0] = "s.Time";
        arr[5][0] = "s.Date";
        arr[6][0] = "c.TypeID";
        arr[7][0] = "m.Genre";

        arr[0][1] = cities;
        arr[1][1] = branches;
        arr[2][1] = cinemas;
        arr[3][1] = movies;
        arr[4][1] = times;
        arr[5][1] = dates;
        arr[6][1] = prices;
        arr[7][1] = genres;

        int field = -1;
        for (int count = 0; count < 8; count++) {
            if (arr[count][1] != null && !arr[count][1].equals("all"))
            {
                field = count;
                break;
            }
        }

        if (field == 4) {
            arr[4][1] = Time.valueOf(LocalTime.parse(arr[4][1])).toString();
        }

        List<Schedule> sched = new ArrayList<Schedule>();

        if (field == -1)
        {
            sched = scheduleService.getSchedules();
        } else {
            sched = scheduleService.findByFieldAndValue(arr[field][0], arr[field][1]);
        }

        model.addAttribute("sched", sched);
       //  return "BrowseMovies";
    }

    @GetMapping("/newMovie")
    public String addMovie(@RequestParam("MovieID") int movieID,
                           @RequestParam("MovieName") String movieName,
                           @RequestParam("Genre") String genre,
                           @RequestParam("Link") String link,
                           @RequestParam("Duration") float duration,
                           @RequestParam("MainChar") String mainChar,
                           @RequestParam("Description") String description) {

        if (movieService.movieExists(movieID))
        {
            System.out.println("Movie ID already exists");
            return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp if movie ID already exists
        }

        Movie movie = new Movie(movieID, movieName, genre, link, duration, mainChar, description);
        movieService.addNewMovie(movie);

        System.out.println("After data");
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful addition
    }

    @GetMapping("/showMovie")
    public String scheduleSelection(@RequestParam(value = "choice", required = false) String sID, HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (sID == null) 
        {
            return "redirect:/BrowseMovies.jsp";
        } else {
            List<Schedule> s = new ArrayList<>();
            s = scheduleService.findByFieldAndValue("s.ScheduleID", sID);
            HttpSession session = req.getSession();
            session.setAttribute("selection", s);
            return "redirect:/MovieInfo.jsp";
        }
    }
}
