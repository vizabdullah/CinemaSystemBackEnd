package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Models.Seat;
import SRE.Project.TMS.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService
{
    @Autowired
    private SeatRepository seat_repo;

    public Seat getSeat(long id)
    {
        return seat_repo.findById(id).orElse(null);
    }

    public List<Seat> getSeats()
    {
        return seat_repo.findAll();
    }
    public List<Seat> getFreeSeats(int s)
    {
        return seat_repo.getFreeSeats(s);
    }
}
