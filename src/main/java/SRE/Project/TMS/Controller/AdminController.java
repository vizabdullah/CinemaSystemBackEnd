package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.*;
import SRE.Project.TMS.Services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("api/admin")
public class AdminController
{
    @Autowired
    private AccountService accountService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private TypeDetailsService typeDetailsService;

    @GetMapping("/approveAccount")
    public void approveAccount(@RequestParam("approveEmail") String email, HttpServletResponse response) throws IOException
    {
        accountService.approvePendingAccounts(email);
        System.out.println(email + " approved");
    }

    @GetMapping("/editMovie")
    public void editMovie(@RequestParam("oldMID") int oldMID,
                          @RequestParam("MovieName") String movieName,
                          @RequestParam("Genre") String genre,
                          @RequestParam("Link") String link,
                          @RequestParam("Duration") float duration,
                          @RequestParam("MainChar") String mainChar,
                          @RequestParam("Description") String description,
                          HttpServletResponse response) throws IOException {

        if (!movieService.movieExists(oldMID))
        {
            System.out.println("Movie id does not exist");
            return;
        }
        Movie movie = new Movie(oldMID, movieName, genre, link, duration, mainChar, description);
        movieService.editMovie(movie, oldMID);

    }

    @GetMapping("/editSchedule")
    public void editSchedule(@RequestParam("CinemaID") int cinemaID,
                             @RequestParam("MovieID") int movieID,
                             @RequestParam("Time") LocalTime time,
                             @RequestParam("Date") LocalDate date,
                             @RequestParam("oldSID") int oldSID,
                             HttpServletResponse response) throws IOException {

        if (!scheduleService.scheduleExists(oldSID)) {
            System.out.println("Schedule ID does not exist");
            response.sendRedirect("AdminHome.jsp");
            return;
        }

        Schedule schedule = new Schedule(oldSID, time,date,cinemaService.getCinema(cinemaID), movieService.getMovie(movieID));
        scheduleService.editSchedule(schedule, oldSID);
    }

    @GetMapping("/editTypeDetails")
    public void editTypeDetails(@RequestParam("oldTID") int oldTID,
                                @RequestParam("Type") String type,
                                @RequestParam("Price") int price,
                                HttpServletResponse response) throws IOException {

        if (!typeDetailsService.typeExists(oldTID)) {
            System.out.println("Type ID does not exist");
            response.sendRedirect("AdminHome.jsp");
            return;
        }

        TypeDetails typeDetails = new TypeDetails(oldTID, type, price);

        typeDetailsService.editType(typeDetails, oldTID);
    }
}
