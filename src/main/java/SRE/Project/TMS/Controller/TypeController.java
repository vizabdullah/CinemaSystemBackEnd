package SRE.Project.TMS.Controller;

import SRE.Project.TMS.Models.TypeDetails;
import SRE.Project.TMS.Services.TypeDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("api/type")
public class TypeController
{
    @Autowired
    private TypeDetailsService typeDetailsService;
    @PostMapping("getTypeController")
    public String handleFormSubmission(@RequestParam("formName") String formName,
                                       @RequestParam("TypeID") int typeID,
                                       @RequestParam("Type") String type,
                                       @RequestParam("Price") int price,
                                       @RequestParam(value = "oldTID", required = false) Integer oldTID) {
        if ("newType".equals(formName)) {
            return newTypeDetail(typeID, type, price);
        } else if ("editType".equals(formName) && oldTID != null) {
            return editTypeDetail(typeID, type, price, oldTID);
        } else {
            // Handle invalid formName or missing oldTID parameter
            return "error";
        }
    }

    private String newTypeDetail(int typeID, String type, int price)
    {
        TypeDetails typeDetails = new TypeDetails(typeID, type, price);
        typeDetailsService.addNewType(typeDetails);
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful addition
    }

    private String editTypeDetail(int typeID, String type, int price, int oldTID)
    {
        TypeDetails typeDetails = new TypeDetails(typeID, type, price);
        typeDetailsService.editType(typeDetails, oldTID);
        return "redirect:/AdminHome.jsp"; // Redirect to AdminHome.jsp after successful edition
    }

    @GetMapping("/newTypeController")
    public void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());

        int TypeID = Integer.parseInt(request.getParameter("TypeID"));
        String Type = request.getParameter("Type");
        int Price = Integer.parseInt(request.getParameter("Price"));

        if (typeDetailsService.typeExists(TypeID)) {
            System.out.println("Type ID already exists");
            response.sendRedirect("AdminHome.jsp");
            return;
        }

        TypeDetails scd = new TypeDetails(TypeID, Type, Price);

        typeDetailsService.addNewType(scd);

        response.sendRedirect("AdminHome.jsp");
    }
}
