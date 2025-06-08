package pers.design.pattern.utils;

import org.springframework.stereotype.Component;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
@Component
public class MaskUtil {
    public static String maskId(String id) {
        if (id == null || id.length() <= 4){
            return "****";
        }
        int showLength = 4;
        return id.substring(0, showLength) + "****" + id.substring(id.length() - showLength);
    }

    public static String maskUsername(String username) {
        if (username == null || username.length() <= 4) {
            return "○○○";
        }
        int showLength = 1;
        return username.substring(0, showLength) + "○" + username.substring(username.length() - showLength);
    }
}
