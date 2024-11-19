package ServerPackage;
import java.util.Set;
public class Student implements User {
    private Set<Permission> permissions = Set.of(Permission.VIEWGRADE);

    public Set getPermissions(){
        return permissions;
    }
}
