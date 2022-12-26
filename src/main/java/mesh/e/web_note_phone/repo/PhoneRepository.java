package mesh.e.web_note_phone.repo;

import mesh.e.web_note_phone.models.Phone;
import mesh.e.web_note_phone.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhoneRepository extends CrudRepository<Phone, Long> {


    List<Phone> findByName(String name);

    List<Phone> findByUserId(User id);

    List<Phone> findByUserIdAndName(User id, String name);



}

