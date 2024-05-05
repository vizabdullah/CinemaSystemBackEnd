package SRE.Project.TMS.Models;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    private String email;
    private String pass;
    private String accountName;
    private LocalDate dob;
    private String phone;
    private String type;

    public Account() {
    }

    public Account(String email, String pass, String accountname, LocalDate dob, String phone, String type) {

        this.email = email;
        this.pass = pass;
        this.accountName = accountname;
        this.dob = dob;
        this.phone = phone;
        this.type = type;
    }
}
