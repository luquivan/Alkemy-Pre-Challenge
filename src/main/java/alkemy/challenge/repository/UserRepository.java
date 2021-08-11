package alkemy.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import alkemy.challenge.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
