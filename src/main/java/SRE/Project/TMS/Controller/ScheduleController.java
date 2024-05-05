package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Services.CinemaService;
import SRE.Project.TMS.Services.MovieService;
import SRE.Project.TMS.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("api/schedule")
public class ScheduleController
{
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private MovieService movieService;

    @PostMapping("getScheduleController")
    public String handleFormSubmission(@RequestParam("formName") String formName,
                                       @RequestParam("ScheduleID") int scheduleID,
                                       @RequestParam("CinemaID") int cinemaID,
                                       @RequestParam("MovieID") int movieID,
                                       @RequestParam("Time") String timeStr,
                                       @RequestParam("Date") String dateStr,
                                       @RequestParam(value = "oldSID", required = false) Integer oldSID)
    {
        if ("newSchedule".equals(formName))
        {
            return newSchedule(scheduleID, cinemaID, movieID, timeStr, dateStr);
        }
        else if ("editSchedule".equals(formName) && oldSID != null)
        {
            return editSchedule(scheduleID, cinemaID, movieID, timeStr, dateStr, oldSID);
        }
        else
        {
            // Handle invalid formName or missing oldSID parameter
            return "error";
        }
    }

    private String newSchedule(int scheduleID, int cinemaID, int movieID, String timeStr, String dateStr) {
        LocalTime time = LocalTime.parse(timeStr);
        LocalDate date = LocalDate.parse(dateStr);
        Schedule schedule = new Schedule(scheduleID, time, date,cinemaService.getCinema(cinemaID), movieService.getMovie(movieID));
        scheduleService.addNewSchedule(schedule);
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful addition
    }

    private String editSchedule(int scheduleID, int cinemaID, int movieID, String timeStr, String dateStr, int oldSID) {
        LocalTime time = LocalTime.parse(timeStr);
        LocalDate date = LocalDate.parse(dateStr);
        Schedule schedule = new Schedule(scheduleID, time, date,cinemaService.getCinema(cinemaID), movieService.getMovie(movieID));
        scheduleService.editSchedule(schedule, oldSID);
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful edition
    }

    @GetMapping("/newSchedule")
    public String addSchedule(@RequestParam("ScheduleID") int scheduleID,
                              @RequestParam("CinemaID") int cinemaID,
                              @RequestParam("MovieID") int movieID,
                              @RequestParam("Time") String timeStr,
                              @RequestParam("Date") String dateStr)
    {


        System.out.println("Before data");

        LocalTime time = LocalTime.parse(timeStr);
        LocalDate date = LocalDate.parse(dateStr);

        if (scheduleService.scheduleExists(scheduleID)) {
            System.out.println("Schedule ID already exists");
            return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp if schedule ID already exists
        }

        Schedule schedule = new Schedule(scheduleID, time, date,cinemaService.getCinema(cinemaID), movieService.getMovie(movieID));
        scheduleService.addNewSchedule(schedule);

        System.out.println("After data");
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful addition
    }

}
