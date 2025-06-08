package pers.design.pattern.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author memorykghs
 * @date 2025/6/7
 */
@Accessors(chain = true)
@Data
public class UserDAO {
    private String username;
    private String encryptedPassword;
    private String id;
    private String email;
}