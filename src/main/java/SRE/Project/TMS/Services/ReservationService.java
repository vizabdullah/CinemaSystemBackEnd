package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.*;
import SRE.Project.TMS.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService
{
    @Autowired
    private ReservationRepository reservation_repo;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GuestService guestService;

    public List<Reservation> getReservations()
    {
        return reservation_repo.findAll();
    }
    public Reservation getReservation(long id)
    {
        return reservation_repo.findById(id).orElse(null);
    }
    public long getReservationID(long schedID, long seatID)
    {
        Schedule schedule = scheduleService.getSchedule(schedID);
        Seat seat = seatService.getSeat(seatID);

        Reservation existing_reservation = reservation_repo.findByScheduleAndSeat(schedule,seat);
        return existing_reservation.getReservationId();
    }

    public long addReservation(long scID, long sID, String email)
    {
        Schedule schedule = scheduleService.getSchedule(scID);
        Seat seat = seatService.getSeat(sID);

        Reservation new_reservation = new Reservation();

        if(accountService.checkAccountExist(email))
        {
            new_reservation.setAccount(accountService.getAccount(email));
            new_reservation.setSeat(seat);
            new_reservation.setSchedule(schedule);
            reservation_repo.save(new_reservation);
            return new_reservation.getReservationId();
        }
        else
        {
            new_reservation.setGuest(guestService.getGuest(email));
            new_reservation.setSeat(seat);
            new_reservation.setSchedule(schedule);
            reservation_repo.save(new_reservation);
            return new_reservation.getReservationId();
        }
    }


}
