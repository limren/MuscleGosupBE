package muscleGosup.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import muscleGosup.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
