package pers.design.pattern.utils;

/**
 * @author memorykghs
 * @date 2025/5/25
 */
public class ApplicationContext {
    private static final ThreadLocal<String> CURRENT_USER_ROLE = new ThreadLocal<>();
    private static final ThreadLocal<String> CURRENT_PASSWORD = new ThreadLocal<>();

    public static void setBasicInfo(String role, String password) {
        CURRENT_USER_ROLE.set(role);
        CURRENT_PASSWORD.set(password);
    }

    public static String getCurrentUserRole() {
        return CURRENT_USER_ROLE.get();
    }

    public static String getCurrentPassword() {
        return CURRENT_PASSWORD.get();
    }

    public static void clear() {
        CURRENT_USER_ROLE.remove();
        CURRENT_PASSWORD.remove();
    }
}
