package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Cinema;
import SRE.Project.TMS.Repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService
{
    @Autowired
    private CinemaRepository cinema_repo;

    public List<Cinema> getCinemas()
    {
        return cinema_repo.findAll();
    }

    public Cinema getCinema(long id)
    {
        return cinema_repo.findById(id).orElse(null);
    }
}
