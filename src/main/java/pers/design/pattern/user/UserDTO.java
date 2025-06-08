package pers.design.pattern.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author memorykghs
 * @date 2025/6/7
 */
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String password;
    private String id;
    private String email;
}
