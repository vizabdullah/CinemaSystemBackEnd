package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.Account;
import SRE.Project.TMS.Services.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("api/account")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @PostMapping("/signup")
    public void signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("pass") String password,
                         @RequestParam("re_pass") String rePassword,
                         @RequestParam("contact") String contact,
                         @RequestParam("dob") String dob,
                         @RequestParam("type") String type,
                         HttpSession session) throws IOException {

        if (accountService.checkAccountsForNewEntry(email) && rePassword.equals(password))
        {
            if (type.equals("Admin"))
            {
                type = "Pending";
            }
            session.setAttribute("status", "success");
            accountService.addNewAccount(name, email, password, contact, LocalDate.parse(dob), type);

        }
        else if (accountService.checkAccountsForNewEntry(email))
        {
            session.setAttribute("status", "Unmatched Password");
        } else
        {
            session.setAttribute("status", "Email Already Exist");
        }
    }

    @PostMapping("/login")
    public void logIn(@RequestParam("username") String email,
                        @RequestParam("password") String password,
                        HttpSession session) throws IOException
    {

        Account account = accountService.checkAccountForLogin(email, password);

        if(account.getType().equals("CEO"))
        {
            session.setAttribute("statuss", "Logged In");
            session.setAttribute("Log_In", "CEO");
            session.setAttribute("Person", "P");
            session.setAttribute("Name", email);
            // return "redirect:/FastHome.jsp";
        }

        else if(account.getType().equals("Manager"))
        {
            session.setAttribute("statuss", "Logged In");
            session.setAttribute("Log_In", "Manager");
            session.setAttribute("Person", "M");
            session.setAttribute("Name", email);
            // return "redirect:/AdminHome.jsp";
        }

        else if(account.getType().equals("Pending"))
        {
            session.setAttribute("statuss", "Account not activated");
            // return "redirect:/LogIn.jsp";
        }

        else if(account.getType().equals("Customer"))
        {
            session.setAttribute("statuss", "Logged In");
            session.setAttribute("Log_In", "Customer");
            session.setAttribute("Person", "C");
            session.setAttribute("Name", email);
            // return "redirect:/index.jsp";
        }

        else if(account.getPass().equals(password))
        {
            session.setAttribute("statuss", "Wrong Password");
            // return "redirect:/LogIn.jsp";
        }

        else
        {
            session.setAttribute("statuss", "User Not Exist, Kindly Register");
            // return "redirect:/LogIn.jsp";
        }

       // return "redirect:/LogIn.jsp";
    }
    @GetMapping("/logOut")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        session.removeAttribute("Log_In");
        session.removeAttribute("statuss");
        session.removeAttribute("Name");
        session.invalidate();
        //return "redirect:/index.jsp";
    }
}
