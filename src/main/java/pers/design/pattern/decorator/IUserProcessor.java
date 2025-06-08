package pers.design.pattern.decorator;

import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public interface IUserProcessor {
    UserDTO mask(UserDTO dto);
}
