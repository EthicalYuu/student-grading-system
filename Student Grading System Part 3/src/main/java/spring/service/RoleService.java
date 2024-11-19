package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.RoleRepo;
import spring.controller.SecurePasswordStorage;
import spring.entity.Role;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    SecurePasswordStorage securePassword = new SecurePasswordStorage();

    public Role getRole(String userId){
        Optional<Role> roles = roleRepo.findByUserId(Integer.parseInt(userId));
        return roles.stream().findFirst().get();
    }

    public boolean validateEntry(String userId, String password) throws Exception {
        Optional<Role> role = roleRepo.findByUserId(Integer.parseInt(userId));
        String salt = role.stream().findFirst().get().getSalt();
        String realPassword = role.stream().findFirst().get().getPassword();
        return securePassword.authenticateUser(password, realPassword, salt);
    }

    public boolean userExists(String userId){
        Optional<Role> roles = roleRepo.findByUserId(Integer.parseInt(userId));
        return roles.isPresent();
    }

    public void createNewRole(String userId, String roleName, String password) throws Exception {
        String salt = securePassword.getNewSalt();
        String encryptedPassword = securePassword.getEncryptedPassword(password, salt);

        Role role = new Role();
        role.setUserId(Integer.parseInt(userId));
        role.setRoleName(roleName);
        role.setPassword(encryptedPassword);
        role.setSalt(salt);
        roleRepo.save(role);
    }

    @Transactional
    public void deleteByUserId(String userId){
        roleRepo.deleteByUserId(Integer.parseInt(userId));
    }

    public boolean isRoleName(String userId, String roleName){
        Optional<Role> role = roleRepo.findByUserIdAndRoleName(Integer.parseInt(userId), roleName);
        return role.isPresent();
    }

}
