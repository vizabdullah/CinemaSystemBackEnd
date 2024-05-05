package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Long>
{

}
