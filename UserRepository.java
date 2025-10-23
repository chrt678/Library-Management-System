package in.rahul.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rahul.main.entity.User;

public interface UserRepository extends JpaRepository<User, Long>

{

	Optional<User> findByUsername(String username);

}
