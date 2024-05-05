package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.History;
import SRE.Project.TMS.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService
{
    @Autowired
    private HistoryRepository history_repo;

    public List<History> getHistory()
    {
        return history_repo.findAll();
    }
    public int getRevenue()
    {
        return history_repo.getRevenue();
    }
}
