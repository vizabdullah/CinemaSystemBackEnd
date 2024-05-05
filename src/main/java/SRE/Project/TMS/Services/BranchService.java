package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Branch;
import SRE.Project.TMS.Repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BranchService
{
    @Autowired
    private BranchRepository branch_repo;

    public List<Branch> getBranches()
    {
        return branch_repo.findAll();
    }
    public Branch getBranch(long id)
    {
        return branch_repo.findById(id).orElse(null);
    }
}
