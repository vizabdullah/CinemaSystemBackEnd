package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepository extends JpaRepository <History,Long>
{

    @Query("select sum(h.Price) from History h")
    public int getRevenue();
}
