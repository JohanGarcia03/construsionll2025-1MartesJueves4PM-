package app.domain.utils;

import app.domain.models.User;

public class RoleValidator {

    public static void validateRole(User user, String requiredRole) throws Exception {
        if (!requiredRole.equalsIgnoreCase(user.getRole())) {
            throw new Exception("🔒 Acceso denegado. Se requiere el rol: " + requiredRole);
        }
    }

    public static boolean hasRole(User user, String role) {
        return role.equalsIgnoreCase(user.getRole());
    }

    public static boolean isAdmin(User user) {
        return "administrador".equalsIgnoreCase(user.getRole());
    }

    public static boolean isVeterinarian(User user) {
        return "veterinario".equalsIgnoreCase(user.getRole());
    }

    public static boolean isSeller(User user) {
        return "vendedor".equalsIgnoreCase(user.getRole());
    }
}
