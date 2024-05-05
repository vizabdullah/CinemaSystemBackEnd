package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Reservation;
import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long>
{
    Reservation findByScheduleAndSeat(Schedule sch, Seat seat);
}
