package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Reservation;
import SRE.Project.TMS.Services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;

@Controller
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/getBookingInfo")
    public void getBookingInfo(@RequestParam("e_ticket") long eTicket,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        Reservation reservation = reservationService.getReservation(eTicket);
        session.setAttribute("booking", reservation);
        //"redirect:/SearchBooking.jsp";

    }
}
