package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@Controller
@RequestMapping("api/seat")
public class SeatController
{
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/selectSeat")
    public String ticketBooking(HttpServletRequest request, HttpSession session, Model model) throws IOException
    {
        if (session.getAttribute("Person") == null || session.getAttribute("Name") == null)
        {
            return "redirect:/LogIn.jsp";
        }


        String id = (String) session.getAttribute("Person");

        List<Schedule> as = (ArrayList<Schedule>) session.getAttribute("selection");
        Schedule s = as.get(0);

        ArrayList<Long> ticketArr = new ArrayList<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements())
        {
            String paramName = (String) paramNames.nextElement();
            try
            {
                ticketArr.add(reservationService.addReservation(s.getScheduleID(), Long.parseLong(paramName), id));
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }

        model.addAttribute("tickets", ticketArr);
        return "redirect:/SeatBooking.jsp";
    }
}
