package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long>
{

    Guest findByGuestEmail(String email);
}
