package pers.design.pattern.adapter;

import pers.design.pattern.user.UserDAO;
import pers.design.pattern.user.UserDTO;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
public interface IUserAdapter {
    UserDTO toDTO(UserDAO userDAO);

    UserDAO toDAO(UserDTO userDTO);
}
