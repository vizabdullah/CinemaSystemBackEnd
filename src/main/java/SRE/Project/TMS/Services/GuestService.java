package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Guest;
import SRE.Project.TMS.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService
{
    @Autowired
    private GuestRepository guest_repo;

    public Guest getGuest(String email)
    {
        return guest_repo.findByGuestEmail(email);
    }
}
