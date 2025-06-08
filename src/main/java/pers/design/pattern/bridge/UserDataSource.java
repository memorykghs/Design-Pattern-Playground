package pers.design.pattern.bridge;

import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public interface UserDataSource {
    UserDTO fetch(String userId);
}
