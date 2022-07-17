package in.stackroute.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stackroute.ms.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
