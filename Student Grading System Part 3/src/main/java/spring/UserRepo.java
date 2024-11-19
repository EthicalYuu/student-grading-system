package spring;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    void deleteByUserId(int userId);

}
