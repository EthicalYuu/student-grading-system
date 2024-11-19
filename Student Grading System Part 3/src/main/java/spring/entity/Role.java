package spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "salt")
    private String salt;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
