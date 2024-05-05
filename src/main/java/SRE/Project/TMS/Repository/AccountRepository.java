package SRE.Project.TMS.Repository;

import SRE.Project.TMS.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long>
{

    List<Account> findByType(String type);

    boolean existsByEmail(String email);

    Account findByEmailAndPass(String email, String pass);

    Account findByEmail(String email);


}
