package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>
{
    @Query("SELECT s FROM Schedule s WHERE CONCAT(s.date, ' ', s.time) > CONCAT(CURRENT_DATE, ' ', CURRENT_TIME)")
    List<Schedule> getLatestSchedule();

    @Query("SELECT s FROM Schedule s " +
            "JOIN s.cinema c " +
            "JOIN s.movie m " +
            "JOIN c.branch b " +
            "JOIN c.type t " +
            "WHERE :field = :value")
    List<Schedule> findByFieldAndValue(String field, String value);
}
