package spring;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.Role;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByUserIdAndPassword(int userId, String password);

    Optional<Role> findByUserId(int userId);

    void deleteByUserId(int userId);

    Optional<Role> findByUserIdAndRoleName(int userId, String roleName);

}
