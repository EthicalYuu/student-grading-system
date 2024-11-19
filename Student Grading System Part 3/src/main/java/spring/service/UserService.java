package spring.service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.UserRepo;
import spring.entity.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void createNewUser(String userId, String firstName, String lastName){
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepo.save(user);
    }

    @Transactional
    public void deleteUserById(String userId){
        userRepo.deleteByUserId(Integer.parseInt(userId));
    }

}
