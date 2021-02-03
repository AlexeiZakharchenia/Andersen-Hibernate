package utils;

import java.util.List;

public interface UserUtils {
    boolean isValid(String userName, String pass);
    boolean hasRole(String userName, String roleName);
    List<String> getAllOtherRoles(String userName, String excepted);
}

