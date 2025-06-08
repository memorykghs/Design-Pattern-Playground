package pers.design.pattern.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.design.pattern.user.UserDAO;
import pers.design.pattern.user.UserDTO;
import pers.design.pattern.utils.AESUtil;

/**
 * @author memorykghs
 * @date 2025/6/8
 */
@Component
@Slf4j
public class UserAdapter implements IUserAdapter {

    @Override
    public UserDTO toDTO(UserDAO userDAO) {
        if (userDAO == null) {
            return null;
        }

        log.info("Decrypting user data for username: {}", userDAO.getUsername());
        return new UserDTO()
            .setUsername(userDAO.getUsername())
            .setPassword(AESUtil.decrypt(userDAO.getEncryptedPassword()))
            .setId(userDAO.getId())
            .setEmail(userDAO.getEmail());
    }

    @Override
    public UserDAO toDAO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        log.info("Encrypting user data for username: {}", userDTO.getUsername());
        return new UserDAO()
            .setUsername(userDTO.getUsername())
            .setEncryptedPassword(AESUtil.encrypt(userDTO.getPassword()))
            .setId(userDTO.getId())
            .setEmail(userDTO.getEmail());
    }
}
