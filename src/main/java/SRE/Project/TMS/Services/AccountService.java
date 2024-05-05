package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.Account;
import SRE.Project.TMS.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService
{
    @Autowired
    private AccountRepository account_repo;

    public List<Account> getCeoName()
    {
        return account_repo.findByType("CEO");
    }
    public List<Account> getAdminName()
    {
        return account_repo.findByType("Manager");
    }
    public List<Account> getPendingAccounts()
    {
        return account_repo.findByType("Pending");
    }

    public boolean checkAccountsForNewEntry(String checkEmail)
    {
        return account_repo.existsByEmail(checkEmail);
    }

    public void addNewAccount(String name, String email, String pass, String phone, LocalDate Dob, String type)
    {

        account_repo.save(new Account(email,pass,name,Dob,phone,type));

    }

    public Account checkAccountForLogin(String email,String pass)
    {

        return account_repo.findByEmailAndPass(email,pass);

    }
    public boolean approvePendingAccounts(String email)
    {
        Account existing_account = account_repo.findByEmail(email);
        if(existing_account == null)
        {
            return false;
        }
        existing_account.setType("Manager");
        account_repo.save(existing_account);
        return true;
    }

    public boolean checkAccountExist(String email)
    {
        return account_repo.existsByEmail(email);
    }

    public Account getAccount(String email)
    {
        return account_repo.findByEmail(email);
    }
}
