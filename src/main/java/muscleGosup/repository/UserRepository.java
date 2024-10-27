package muscleGosup.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import muscleGosup.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
