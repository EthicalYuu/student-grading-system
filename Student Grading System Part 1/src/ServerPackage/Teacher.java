package ServerPackage;
import java.util.Set;

public class Teacher implements User {

    private Set<Permission> permissions = Set.of(Permission.VIEWGRADE, Permission.MODIFYGRADE);

    @Override
    public Set getPermissions() {
        return permissions;
    }
}
