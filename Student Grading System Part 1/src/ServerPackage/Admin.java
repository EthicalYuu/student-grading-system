package ServerPackage;
import java.sql.Connection;
import java.util.Set;

public class Admin implements User {

    final private Set<Permission> permissions = Set.of(Permission.CREATECOURSE, Permission.DELETECOURSE, Permission.CREATEUSER, Permission.DELETEUSER);

    @Override
    public Set getPermissions() {
        return permissions;
    }

}
