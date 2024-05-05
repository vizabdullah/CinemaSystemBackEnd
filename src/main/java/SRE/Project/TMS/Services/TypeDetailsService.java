package SRE.Project.TMS.Services;

import SRE.Project.TMS.Models.TypeDetails;
import SRE.Project.TMS.Repository.TypeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDetailsService
{
    @Autowired
    private TypeDetailsRepository type_repo;

    public boolean typeExists(long id)
    {
        return type_repo.existsById(id);
    }

    public List<TypeDetails> getTypeDetails()
    {
        return type_repo.findAll();
    }
    public TypeDetails getTypeDetail(long id)
    {
        return type_repo.findById(id).orElse(null);
    }
    public void addNewType(TypeDetails td)
    {
        type_repo.save(td);
    }
    public boolean editType(TypeDetails td, long typeID)
    {
        TypeDetails existing_type = type_repo.findById(typeID).orElse(null);
        if(existing_type == null)
        {
            return false;
        }
        else
        {
            existing_type.setType(td.getType());
            existing_type.setPrice(td.getPrice());
            type_repo.save(existing_type);
            return true;
        }
    }
}
