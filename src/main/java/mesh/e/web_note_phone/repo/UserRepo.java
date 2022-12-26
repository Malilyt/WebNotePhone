package mesh.e.web_note_phone.repo;

import mesh.e.web_note_phone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
