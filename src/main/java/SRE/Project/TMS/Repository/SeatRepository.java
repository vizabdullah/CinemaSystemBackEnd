package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long>
{
    @Query("SELECT s FROM Seat s, Schedule sc, Cinema c where sc.cinema.CinemaID = c.CinemaID and s.cinema.CinemaID = c.CinemaID and sc.scheduleID = :scheduleId and s.seatID not in (select r.seat.seatID from Reservation r where r.schedule.scheduleID = :scheduleId)")
    List<Seat> getFreeSeats(@Param("scheduleId") long scheduleId);
}
