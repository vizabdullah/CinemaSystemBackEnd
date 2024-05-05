package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Schedule;
import SRE.Project.TMS.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService
{
    @Autowired
    private ScheduleRepository schedule_repo;

    public Schedule getSchedule(long id)
    {
        return schedule_repo.findById(id).orElse(null);
    }
    public List<Schedule> getSchedules()
    {
        return schedule_repo.findAll();
    }

    public boolean addNewSchedule(Schedule s)
    {
         schedule_repo.save(s);
         return true;
    }
    public boolean editSchedule(Schedule s, long oldSID)
    {
        Schedule existing_schedule = schedule_repo.findById(oldSID).orElse(null);
        if(existing_schedule == null)
        {
            return false;
        }
        else
        {
            existing_schedule.setCinema(s.getCinema());
            existing_schedule.setMovie(s.getMovie());
            existing_schedule.setDate(s.getDate());
            existing_schedule.setTime(s.getTime());
            return true;
        }
    }
    public List<Schedule> getLatestSchedule()
    {
        return schedule_repo.getLatestSchedule();
    }

    public List<Schedule> findByFieldAndValue(String field, String value)
    {
        return schedule_repo.findByFieldAndValue(field,value);
    }
    public boolean scheduleExists(long schedID)
    {
        return schedule_repo.existsById(schedID);
    }
}
